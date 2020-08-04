package com.hyd.monthreport.service;

import com.hyd.monthreport.info.*;
import com.hyd.monthreport.mapper.MonthBaseMapper;
import com.hyd.monthreport.mapper.MonthQueMapper;
import com.hyd.monthreport.mapper.MonthRepMapper;
import com.hyd.monthreport.vo.*;
import com.hyd.system.exceptclass.BusineException;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.util.CollectionUtils;
import com.hyd.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Created by xieshuai on 2020/4/10 17:08
 */

@Service
public class MonthQueService {

    @Autowired
    private MonthQueMapper monthQueMapper;

    @Autowired
    private MonthRepMapper monthRepMapper;

    @Autowired
    private MonthBaseMapper  monthBaseMapper;

    @Autowired
    private StringUtils stringUtils;


    /**
     * 自定义月报查询-- 查询所有视图
     * @return
     */
    public MonthQueVO monthQuertyTemplate(QueryMonthInfo info){
        MonthQueVO monthQueVO = new MonthQueVO();

        List<Map<String, Object>> strViewList = new ArrayList<>(16);
        List<Map<String, Object>> orgViewList = new ArrayList<>(16);
        List<Map<String, Object>> regViewList = new ArrayList<>(16);
        if(info.getAreaList().size()>0){
            //设置片区数据
            for(MonthAreaInfo areaInfo: info.getAreaList()){
                Map<String, Object> strAreaMap = new HashMap<>(16);
                Map<String, Object> orgAreaMap = new HashMap<>(16);
                Map<String, Object> regAreaMap = new HashMap<>(16);

                //片区-->街道
                strAreaMap.put("id", areaInfo.getAreaId());
                strAreaMap.put("title", areaInfo.getAreaNam());
                strAreaMap.put("pid", "1");

                //片区-->机构
                orgAreaMap.put("id", areaInfo.getAreaId());
                orgAreaMap.put("title", areaInfo.getAreaNam());
                orgAreaMap.put("pid", "1");

                //片区-->市-->区/县-->街道/乡镇-->机构
                regAreaMap.put("id", areaInfo.getAreaId());
                regAreaMap.put("title", areaInfo.getAreaNam());
                regAreaMap.put("pid", "1");

                List<Map<String, Object>> strAreaZoneList = new ArrayList<>(16);
                List<Map<String, Object>> orgAreaZoneList = new ArrayList<>(16);
                List<Map<String, Object>> regAreaZoneList = new ArrayList<>(16);

                List<Map<String, String>> orgareaChilenList = new ArrayList<>(16);

                //查询市级信息
                List<ZoneVO> citys = monthQueMapper.queryCityByAreaCode(areaInfo.getAreaId());

                if(citys.size()>0){
                    for(ZoneVO city: citys){
                        //查询区县信息
                        List<Map<String, Object>> regAreaCountyList = new ArrayList<>(16);
                        List<ZoneVO> countys = monthQueMapper.queryCountyByAreaCode(areaInfo.getAreaId(), city.getId());
                        //行政区域->市级数据
                        Map<String, Object> regCityMap = new HashMap<>(16);
                        regCityMap.put("id", city.getId());
                        regCityMap.put("title", city.getTitle());
                        regCityMap.put("pid", "2");
                        if(countys.size()>0){
                            for(ZoneVO county: countys){
                                //查询街道信息
                                List<Map<String, Object>> regAreaStreetList = new ArrayList<>(16);
                                List<ZoneVO> streets = monthQueMapper.queryStreetByAreaCd(areaInfo.getAreaId(), county.getId());
                                //街道数据去重
                                Set<ZoneVO> set = new HashSet<>(streets);
                                List<ZoneVO> streetss = new ArrayList<>(set);

                                //行政区域->区县数据
                                Map<String, Object> regCountyMap = new HashMap<>(16);
                                regCountyMap.put("id", county.getId());
                                regCountyMap.put("title", county.getTitle());
                                regCountyMap.put("pid", "3");
                                if(streetss.size()>0){
                                    for(ZoneVO street: streetss){
                                        //行政区域路径->街道数据
                                        Map<String, Object> regStreetMap = new HashMap<>(16);
                                        regStreetMap.put("id", street.getId());
                                        regStreetMap.put("title", street.getTitle());
                                        regStreetMap.put("pid", "4");
                                        List<Map<String, Object>> organs = this.queryMonthBasicData(
                                                street.getId(), info.getYear() + "-" + info.getMonth(), info.getTypeNam());
                                        regStreetMap.put("children", this.listMapValuesToString(organs));
                                        Map<String, Object> streetResultMap = CollectionUtils.listSum(organs, "Num\\d+");
                                        regStreetMap.putAll(this.getNumResultMap(streetResultMap));
                                        regAreaStreetList.add(regStreetMap);

                                        //片区->街道
                                        Map<String, Object> strStreetMap = new HashMap<>(16);
                                        strStreetMap.put("id", street.getId());
                                        strStreetMap.put("title", street.getTitle());
                                        strStreetMap.put("pid", "2");

                                        strStreetMap.putAll(this.getNumResultMap(streetResultMap));
                                        strAreaZoneList.add(strStreetMap);

                                        //片区->机构
                                        orgAreaZoneList.addAll(organs);
                                        orgareaChilenList.addAll(this.listMapValuesToString(organs));
                                    }
                                }
                                regCountyMap.put("children", regAreaStreetList);
                                regCountyMap.putAll(this.getNumResultMap(CollectionUtils.listSum(regAreaStreetList, "Num\\d+")));
                                regAreaCountyList.add(regCountyMap);

                            }
                        }
                        regCityMap.put("children", regAreaCountyList);
                        Map<String, Object> objectMap = CollectionUtils.listSum(regAreaCountyList, "Num\\d+");
                        regCityMap.putAll(this.getNumResultMap(objectMap));
                        regAreaZoneList.add(regCityMap);
                    }
                }
                strAreaMap.put("children", strAreaZoneList);
                strAreaMap.putAll(this.getNumResultMap(CollectionUtils.listSum(strAreaZoneList, "Num\\d+")));
                orgAreaMap.put("children", orgareaChilenList);
                orgAreaMap.putAll(this.getNumResultMap(CollectionUtils.listSum(orgAreaZoneList, "Num\\d+")));
                regAreaMap.put("children", regAreaZoneList);
                regAreaMap.putAll(this.getNumResultMap(CollectionUtils.listSum(regAreaZoneList, "Num\\d+")));

                strViewList.add(strAreaMap);
                orgViewList.add(orgAreaMap);
                regViewList.add(regAreaMap);
            }
        }else {
            throw new BusineException("找不到当前片区信息");
        }
        monthQueVO.setRegionsViewTables(regViewList);
        monthQueVO.setStreetViewTables(strViewList);
        monthQueVO.setOrganViewTables(orgViewList);

        return monthQueVO;
    }


    /**
     * 加载片区查询自定义配置项
     * @return
     */
    public List<Map<String, Object>> loadViewConfig(){
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            //查询年份
            List<Map<String, Object>> yearList = monthQueMapper.queryConfigYears();
            for(int i=0; i<yearList.size(); i++){
                Map<String, Object> map = new HashMap<>();
                map.put("yearCode", yearList.get(i).get("ConfigValue"));
                map.put("yearNam", yearList.get(i).get("DescribeValue"));
                map.put("months", monthQueMapper.queryConfigMonths((String)yearList.get(i).get("ConfigValue")));
                list.add(map);
            }
            return list;
        }catch (Exception e){
            throw new BusineException("加载片区查询配置项失败");
        }
    }


    /**
     * 加载当前机构的所有片区
     * @param info
     * @return
     */
    public List<MonthQueAreaVO> loadMonthArea(LoadAuthInfo info){
        try {
            return monthQueMapper.loadMonthArea(info.getThisZoneId());
        }catch (Exception e){
            throw new BusineException("查询片区失败,当前操作地区不存在");
        }
    }


    /**
     * 删除一个已生效的指标片区
     * @param info
     * @return
     */
    public Integer delArea(AreaInfo info){
        try {
            monthQueMapper.delArea(info.getAreaId(), info.getAuthZoneCd());
            return monthQueMapper.delQueZone(info.getAreaId());
        }catch (Exception e){
            throw new BusineException("删除一个片区失败");
        }

    }


    /**
     * 查询当前授权地区的可授权所有街道信息
     * @param info
     * @return
     */
    public List<AuthCityVO> queryLicensable(LoadAuthInfo info){
        if(null==info.getThisZoneId()){
            throw new BusineException("当前用户操作地区编码为空");
        }
        return this.queryAuthZones(info);
    }


    /**
     * 新增一个指标片区
     * @param info
     * @return
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public Integer addArea(AreaInfo info){
        try {
            String areaCd = stringUtils.getCd("Area");
            //1.新增片区表
            monthQueMapper.addArea(areaCd, info.getAreaNam(), info.getAuthZoneCd(), info.getUserCd(), StringUtils.getDateStr(), "1");
            //2.新增地区表
            for(int i=0; i<info.getAreaCityInfos().size(); i++){
                monthQueMapper.addQueZone(areaCd,
                        info.getAreaCityInfos().get(i).getCityCd(),
                        info.getAreaCityInfos().get(i).getCountyCd(),
                        info.getAreaCityInfos().get(i).getStreetCd(),
                        info.getAreaCityInfos().get(i).getIsQuota());
            }
            return null;
        }catch (Exception e){
            throw new BusineException("新增片区失败");
        }
    }


    /**
     * 修改指标片区
     * @param info
     * @return
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    public Integer editArea(AreaInfo info){
        try {
            //先移除
            monthQueMapper.delArea(info.getAreaId(), info.getAuthZoneCd());
            monthQueMapper.delQueZone(info.getAreaId());
            //再添加
            this.addArea(info);
            return null;
        }catch (Exception e){
            throw new BusineException("修改指标片区失败");
        }
    }


    /**
     * 加载当前地区所有可授权街道信息和当前已被授权街道信息
     * @param info
     * @return
     */
    public List<AuthCityVO> queryArea(QueryAreaInfo info){
        List<AuthCityVO> list = new ArrayList<>(16);
        List<StreetVO> cityS = null;
        //查询市级菜单
        try {
            cityS = monthRepMapper.loadAuthorizedCity(info.getThisZoneId());
        }catch (Exception e){
            throw new SqlException("查询市级信息失败");
        }
        //查询市级菜单
        StreetVO city = monthQueMapper.queryThisCity(info.getThisZoneId());
        cityS.add(city);
        cityS.stream().distinct().collect(Collectors.toList());
        if(cityS.size()==0){
            //加载当前地区所有地区信息
            Integer streetCount = 0;
            AuthCityVO cityVO = new AuthCityVO();
            List<AuthCountyVO> countyList= new ArrayList<>(16);
            AuthCountyVO authCountyVO = new AuthCountyVO();
            //1. add 市
            city = monthQueMapper.queryThisCity(info.getThisZoneId());
            //1. add 区县
            StreetVO dist = monthQueMapper.queryThisDistricts(info.getThisZoneId());
            //2. add街道
            List<StreetVO> street = monthQueMapper.queryThisStreets(info.getThisZoneId());
            //查询当前地区是否被选中的状态
            if(null!=info.getAreaCd()) {
                for (int k = 0; k < street.size(); k++) {
                    street.get(k).setIsQuota(monthQueMapper.selectStreetByIsQuota(info.getAreaCd(), street.get(k).getCd()));
                }
            }
            authCountyVO.setStreetVOS(street);
            authCountyVO.setCd(dist.getCd());
            authCountyVO.setNam(dist.getNam());
            countyList.add(authCountyVO);

            cityVO.setNam(city.getNam());
            cityVO.setCd(city.getCd());
            cityVO.setStreetCount(street.size());
            cityVO.setDistrictCount(1);
            cityVO.setCountyList(countyList);
            list.add(cityVO);

        }else {
            for (int i = 0; i < cityS.size(); i++) {

                Integer streetCount = 0;
                AuthCityVO cityVO = new AuthCityVO();
                List<AuthCountyVO> countyList = new ArrayList<>(16);
                List<StreetVO> countyS = new ArrayList<>(16);
                try {
                    countyS = monthRepMapper.loadAuthorizedCounty(cityS.get(i).getCd(), info.getThisZoneId());
                } catch (Exception e) {
                    throw new SqlException("查询区县信息失败");
                }

                cityVO.setCd(cityS.get(i).getCd());
                cityVO.setNam(cityS.get(i).getNam());
                //根据当前市编码和当前地区编码查询已授权街道数量和已授权区域数量
                cityVO.setDistrictCount(countyS.size());
                cityVO.setStreetCount(countyS.size());
                for (int j = 0; j < countyS.size(); j++) {
                    AuthCountyVO countyVO = new AuthCountyVO();
                    List<StreetVO> streetVOS = new ArrayList<>(16);
                    try {
                        streetVOS = monthRepMapper.loadAuthorizedStreet(countyS.get(j).getCd(), info.getThisZoneId());
                        //查询当前地区是否被选中的状态
                        for (int k = 0; k < streetVOS.size(); k++) {
                            streetVOS.get(k).setIsQuota(monthQueMapper.selectStreetByIsQuota(info.getAreaCd(), streetVOS.get(k).getCd()));
                        }

                    } catch (Exception e) {
                        throw new SqlException("查询街道信息失败");
                    }
                    countyVO.setStreetVOS(streetVOS);
                    countyVO.setCd(countyS.get(j).getCd());
                    countyVO.setNam(countyS.get(j).getNam());
                    countyList.add(countyVO);
                    streetCount += streetVOS.size();
                }

                //添加当前地区的街道信息
                if (cityS.get(i).getCd().equals(city.getCd())) {
                    AuthCountyVO authCountyVO = new AuthCountyVO();
                    city = monthQueMapper.queryThisCity(info.getThisZoneId());
                    //1. add 区县
                    StreetVO dist = monthQueMapper.queryThisDistricts(info.getThisZoneId());
                    //2. add街道
                    List<StreetVO> street = monthQueMapper.queryThisStreets(info.getThisZoneId());
                    //查询当前地区是否被选中的状态
                    for (int k = 0; k < street.size(); k++) {
                        street.get(k).setIsQuota(monthQueMapper.selectStreetByIsQuota(info.getAreaCd(), street.get(k).getCd()));
                    }
                    authCountyVO.setStreetVOS(street);
                    authCountyVO.setCd(dist.getCd());
                    authCountyVO.setNam(dist.getNam());
                    countyList.add(authCountyVO);
                }

                cityVO.setStreetCount(streetCount);
                cityVO.setCountyList(countyList);
                list.add(cityVO);
            }
        }
        list.stream().distinct().collect(Collectors.toList());
        Set<AuthCityVO> set = new HashSet<>(list);
        List<AuthCityVO> newList = new ArrayList<>(set);
        return newList;
    }


    /***
     * 查询基础机构数据
     * @param yearDate
     * @param typeName
     * @return
     */
    protected List<Map<String, Object>> queryMonthBasicData(String streetId, String yearDate, String typeName){
        try {
            switch (typeName){
                case "建档情况":
                    return monthBaseMapper.queryDocumentBaseDate(streetId, yearDate);
                case "一般情况":
                    return monthBaseMapper.queryGenInfoBaseDate(streetId, yearDate);
                case "治疗情况":
                    return monthBaseMapper.queryTreatSitBaseDate(streetId, yearDate);
                case "管理情况":
                    return monthBaseMapper.queryManageBaseDate(streetId, yearDate);
                case "基础管理":
                    return monthBaseMapper.queryBasicManageBaseDate(streetId, yearDate);
                case "个案管理":
                    return monthBaseMapper.queryCaseManageBaseDate(streetId, yearDate);
                case "失访和死亡":
                    return monthBaseMapper.queryLosAndDeathBaseDate(streetId, yearDate);
                default:
                    return null;
            }
        }catch (Exception e){
            throw new SqlException("查询月报基础数据失败");
        }
    }


    /**
     * 从map中获取只含有Num开头的key和value
     * @param objectMap
     * @return
     */
    private Map<String, String> getNumResultMap(Map<String, Object> objectMap ){
        Map<String, Object> resultMap = new HashMap<>(16);
        for(Map.Entry<String, Object> entry : objectMap.entrySet()){
            if(entry.getKey().matches("Num\\d+")){
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        return this.mapValuesToString(resultMap);
    }


    /**
     * 插叙当前机构下可授权的片区信息
     * @param info
     * @return
     */
    private List<AuthCityVO> queryAuthZones(LoadAuthInfo info){
        List<AuthCityVO> list = new ArrayList<>(16);
        List<StreetVO> cityS = null;
        //查询市级菜单
        try {
            cityS = monthRepMapper.loadAuthorizedCity(info.getThisZoneId());
        }catch (Exception e){
            throw new SqlException("查询市级信息失败");
        }
        //查询市级菜单
        StreetVO city = monthQueMapper.queryThisCity(info.getThisZoneId());
        cityS.add(city);
        cityS.stream().distinct().collect(Collectors.toList());
        if(cityS.size()==0){
            //加载当前地区所有地区信息
            Integer streetCount = 0;
            AuthCityVO cityVO = new AuthCityVO();
            List<AuthCountyVO> countyList= new ArrayList<>(16);
            AuthCountyVO authCountyVO = new AuthCountyVO();
            //1. add 市
            city = monthQueMapper.queryThisCity(info.getThisZoneId());
            //1. add 区县
            StreetVO dist = monthQueMapper.queryThisDistricts(info.getThisZoneId());
            //2. add街道
            List<StreetVO> street = monthQueMapper.queryThisStreets(info.getThisZoneId());
            authCountyVO.setStreetVOS(street);
            authCountyVO.setCd(dist.getCd());
            authCountyVO.setNam(dist.getNam());
            countyList.add(authCountyVO);

            cityVO.setNam(city.getNam());
            cityVO.setCd(city.getCd());
            cityVO.setStreetCount(street.size());
            cityVO.setDistrictCount(1);
            cityVO.setCountyList(countyList);
            list.add(cityVO);
        }else {
            //加载当前地区和已被授权的地区
            for(int i=0; i<cityS.size(); i++){


                Integer streetCount = 0;
                AuthCityVO cityVO = new AuthCityVO();
                List<AuthCountyVO> countyList = new ArrayList<>(16);
                List<StreetVO> countyS = new ArrayList<>(16);
                try {
                    countyS = monthRepMapper.loadAuthorizedCounty(cityS.get(i).getCd(), info.getThisZoneId());
                }catch (Exception e){
                    throw new SqlException("查询区县信息失败");
                }

                cityVO.setCd(cityS.get(i).getCd());
                cityVO.setNam(cityS.get(i).getNam());
                //根据当前市编码和当前地区编码查询已授权街道数量和已授权区域数量
                cityVO.setDistrictCount(countyS.size());
                cityVO.setStreetCount(countyS.size());
                for(int j=0; j<countyS.size(); j++){
                    AuthCountyVO countyVO = new AuthCountyVO();
                    List<StreetVO> streetVOS = new ArrayList<>(16);
                    try {
                        streetVOS = monthRepMapper.loadAuthorizedStreet(countyS.get(j).getCd(), info.getThisZoneId());

                    }catch (Exception e){
                        throw new SqlException("查询街道信息失败");
                    }
                    countyVO.setStreetVOS(streetVOS);
                    countyVO.setCd(countyS.get(j).getCd());
                    countyVO.setNam(countyS.get(j).getNam());
                    countyList.add(countyVO);
                    streetCount += streetVOS.size();
                }

                //添加当前地区的街道信息
                if(cityS.get(i).getCd().equals(city.getCd())){
                    AuthCountyVO authCountyVO = new AuthCountyVO();
                    //1. add 区县
                    StreetVO dist = monthQueMapper.queryThisDistricts(info.getThisZoneId());
                    //2. add街道
                    List<StreetVO> street = monthQueMapper.queryThisStreets(info.getThisZoneId());
                    authCountyVO.setStreetVOS(street);
                    authCountyVO.setCd(dist.getCd());
                    authCountyVO.setNam(dist.getNam());
                    countyList.add(authCountyVO);
                }
                countyList.stream().distinct().collect(Collectors.toList());

                cityVO.setStreetCount(streetCount);
                cityVO.setCountyList(countyList);

                list.add(cityVO);
            }
        }
        list.stream().distinct().collect(Collectors.toList());
        Set<AuthCityVO> set = new HashSet<>(list);
        List<AuthCityVO> newList = new ArrayList<>(set);
        return newList;
    }


    /**
     * 将List<Map>中所有values全部转换为String
     * @param
     * @return
     */
    private List<Map<String, String>> listMapValuesToString(List<Map<String, Object>> list){
        return list.stream()
                .map(map ->
                        map.entrySet().stream()
                                .collect(
                                        Collectors.toMap(
                                                Map.Entry::getKey, e -> e.getValue().toString())))
                .collect(Collectors.toList());
    }


    /**
     * 将Map中所有values转为String
     * @param map
     * @return
     */
    private Map<String,String> mapValuesToString(Map<String, Object> map){
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue())));
    }


}