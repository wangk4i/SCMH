package com.hyd.monthreport.service;


import com.hyd.monthreport.info.*;
import com.hyd.monthreport.mapper.MonthRepMapper;
import com.hyd.monthreport.mapper.ZoneMapper;
import com.hyd.monthreport.vo.StreetVO;
import com.hyd.monthreport.vo.ZoneVO;
import com.hyd.monthreport.vo.AuthCityVO;
import com.hyd.monthreport.vo.AuthCountyVO;
import com.hyd.system.exceptclass.BusineException;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieshuai on 2020/4/7 17:04
 */

@Service
public class MonthRepService {

    @Autowired
    private MonthRepMapper monthRepMapper;

    @Autowired
    private ZoneMapper zoneMapper;

    @Autowired
    private StringUtils stringUtils;


    /**
     * 根据区县编码查询符合条件的街道信息
     * @param info
     * @return
     */
    public List<StreetVO> queryStreet(QueryStreetInfo info){
        try {
            return monthRepMapper.queryStreetInfo(info.getCountyId(), info.getThisCountyId());
        }catch (Exception e){
            throw new SqlException("查询街道信息失败");
        }
    }

    /**
     * 根据当前区县编码查询所有符合条件的省 市 区县信息
     * @param info
     * @return
     */
    public List<Map<String, Object>> queryZone(QueryZoneInfo info){
        //查询三级下拉列表信息
        List<ZoneVO> treeMenu = null;
        try {
            treeMenu = zoneMapper.queryZoneByList(info.getProvinceId());
        }catch (Exception e){
            throw new SqlException("加载下拉列表失败");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        if (treeMenu.size() > 0) {
            for (ZoneVO vo : treeMenu) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("id", vo.getId());
                map.put("title", vo.getTitle());
                map.put("parentId", vo.getParentId());
                map.put("levIndex", vo.getLevIndex());

                //查询二三级信息
                map.put("children", getChildrens(vo.getId(), info.getCountyId()));
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 加载所有地区信息
     * @param parCd
     * @return
     */
    public List<Map<String, Object>> loadTreeZone(String parCd){
        return this.tree(parCd);
    }

    /**
     * 加载授权情况
     * @return
     */
    public List<AuthCityVO> loadAuth(LoadAuthInfo info) {

        List<AuthCityVO> list = new ArrayList<>();
        List<StreetVO> cityS = null;
        //查询市级菜单
        try {
            cityS = monthRepMapper.loadAuthorizedCity(info.getThisZoneId());
        }catch (Exception e){
            throw new SqlException("查询市级信息失败");
        }
        for(int i=0; i<cityS.size(); i++){
            Integer streetCount = 0;
            AuthCityVO cityVO = new AuthCityVO();
            List<AuthCountyVO> countyList = new ArrayList<>();
            List<StreetVO> countyS = null;
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
                List<StreetVO> streetVOS = null;
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
            cityVO.setStreetCount(streetCount);
            cityVO.setCountyList(countyList);
            list.add(cityVO);
        }
        return list;
    }

    /**
     * 新增地区授权
     * @param info
     * @return
     */
    public boolean addAuth(AddAuthInfo info){
        if(info.getStreetList().size()==0){
            throw new BusineException("新增地区授权失败,街道信息为空");
        }
        for(int i=0; i<info.getStreetList().size(); i++){
            StreetInfo street = info.getStreetList().get(i);
            String userCd = info.getUserCd();
            String userCdNam = info.getUserCdNam();
            try {
                monthRepMapper.addZoneAuth(stringUtils.getCd("MONREP"), street.getZoneCd(), info.getCityId(), info.getCountyId(), userCd, StringUtils.getDateStr(), "1", info.getThisCountId());
            }catch (Exception e){
                throw new SqlException("新增权限失败");
            }
        }
        return true;
    }

    /**
     * 移除地区授权
     * @param info
     * @return
     *
     *
     */
    public boolean removeAuth(RemoveAuthInfo info){
        try {
            monthRepMapper.removeAuth(info.getThisZoneId(), info.getStreetId());
            return true;
        }catch (Exception e){
            throw new SqlException("移除权限失败");
        }
    }


    private List<Map<String, Object>> tree(String parCd) {
        List<ZoneVO> treeMenu = zoneMapper.queryZoneByList(parCd);
        List<Map<String, Object>> list = new ArrayList<>();
        if (treeMenu.size() > 0) {
            for (ZoneVO vo : treeMenu) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("id", vo.getId());
                map.put("title", vo.getTitle());
                map.put("parentId", vo.getParentId());
                map.put("levIndex", vo.getLevIndex());
                map.put("children", getChildren(vo.getId()));
                list.add(map);
            }
        }
        return list;
    }


    /**
     * 递归
     *
     * @param id
     * @return
     */
    private List<Object> getChildren(String id) {
        List<Object> list = new ArrayList<>();
        List<ZoneVO> treeMenu = zoneMapper. queryZoneByList(id);
        for (ZoneVO vo : treeMenu) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", vo.getId());
            map.put("title", vo.getTitle());
            map.put("parentId", vo.getParentId());
            map.put("levIndex", vo.getLevIndex());
            list.add(map);
        }
        return list;
    }

    private List<Object> getChildrens(String id, String thisId){
        List<Object> list = new ArrayList<>();
        List<ZoneVO> treeMenu = zoneMapper. queryZoneByLists(id, thisId);
        for (ZoneVO vo : treeMenu) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", vo.getId());
            map.put("title", vo.getTitle());
            map.put("parentId", vo.getParentId());
            map.put("levIndex", vo.getLevIndex());
            list.add(map);
        }
        return list;
    }
}
