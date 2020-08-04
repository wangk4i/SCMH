package com.hyd.system.util;


import org.springframework.lang.Nullable;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xieshuai on 2020/4/22 9:48
 * 集合操作工具类
 */
public class CollectionUtils {


    /**
     * list内map中所有相同的key合并数据
     * 对于整形数值,是直接求和。对于小数数值,是求和后再求平均值
     * @param lists 需要合并的list集合
     * @param keyLike list中合并的模糊值key
     * @return
     */
    public static Map<String, Object> listSum(List<Map<String, Object>> lists, String keyLike){
        if(lists.size()==0){
            Map<String, Object> m = new HashMap<>();
            return m;
        }
        if(lists.size()==1){
            return lists.get(0);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i=0; i<lists.size(); i++ ){
            Map<String, Object> hashMap = lists.get(i);
            list.add(i, hashMap.entrySet().stream()
                    .filter(map-> map.getKey().matches(keyLike))
                    .collect(Collectors.toMap(h -> h.getKey(), h-> h.getValue())));
        }

        Map<String, Object> resultMap = new HashMap<>(16);
        Set keys = new HashSet(8);
        for(int i=0; i<list.size(); i++){
            if(list.get(i).size()>0){
                keys = list.get(i).keySet();
                break;
            }
        }
        keys.forEach((e) -> {
            list.forEach((li) -> {
                if (resultMap.get(e) == null) {
                    resultMap.put(e.toString(), 0);
                }
                String s = String.valueOf(li.get(e));
                //判断是否为整数
                 if(s.matches("\\d+")){
                     resultMap.put(e.toString(), (Integer)resultMap.get(e) + (Integer.parseInt( String.valueOf(li.get(e)))));
                 }
                 //判断是否为小数
                 if(!s.matches("\\d+")&&null!=li.get(e)&&!s.equals("")){
                    resultMap.put(e.toString(), Float.parseFloat(String.valueOf(resultMap.get(e))) + Float.parseFloat(String.valueOf(li.get(e))));
                 }
            });
        });

        Map<String, Object> resultSetMap = new HashMap<>(16);
        for(Map.Entry<String, Object> entry : resultMap.entrySet()){
            if(entry.getKey().matches(keyLike)){
                resultSetMap.put(entry.getKey(), entry.getValue());

                if(entry.getValue().getClass().equals(Integer.class)){
                    resultSetMap.put(entry.getKey(), entry.getValue());
                }
                if(entry.getValue().getClass().equals(Float.class)){

                    NumberFormat numberFormat = NumberFormat.getInstance();
                    // 设置精确到小数点后2位
                    numberFormat.setMaximumFractionDigits(2);
                    //所占百分比
                    resultSetMap.put(entry.getKey(), Float.parseFloat(numberFormat.format((Float) entry.getValue() / lists.size())));
                }
            }
        }

        return resultSetMap;
    }

    /**
     * 通过模糊的key获取map中的key集合
     * @param map
     * @param keyLike
     * @return
     */
    public static List<String> getMapKeysByLikeKey(Map<String, Object>map,String keyLike){
        List<String> list= new Vector<>();
        for (Map.Entry<String, Object> entity : map.entrySet()) {
            if(entity.getKey().indexOf(keyLike)>-1){
                list.add(entity.getKey());
            }
        }
        return list;
    }

    /**
     * 通过模糊的key获取map中的values集合
     * @param map
     * @param keyLike
     * @return
     */
    public static List<Object> getMapValuesByLikeKey(Map<String, Object>map,String keyLike){
        List<Object> list= new Vector<>();
        for (Map.Entry<String, Object> entity : map.entrySet()) {
            if(entity.getKey().indexOf(keyLike)>-1){
                list.add(entity.getValue());
            }
        }
        return list;
    }

    public static Map<String, Object> resultMapStaticSum(List<Map<String, Object>> list){
        Map<String, Object> map = new HashMap<>();
        Map map2 = new HashMap<>(map);
        Map<String, String> map3 = (Map) map;
        return map;
    }

    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }


}
