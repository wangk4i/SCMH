package com.hyd.repeatpatmove.util;

import java.util.Iterator;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/4 11:15
 */
public class Tools {
    /**
     * 将参数化为 body
     * @param params
     * @return
     */
    public static String getMapToJson(Map<String, String> params) {
        StringBuilder body = new StringBuilder();
        Iterator<String> iteratorHeader = params.keySet().iterator();
        if (!iteratorHeader.hasNext()) {
            return "";
        }
        body.append("{\n");
        while (iteratorHeader.hasNext()) {
            String key = iteratorHeader.next();
            String value = params.get(key);
            body.append("\t\""+key+"\"" + ":" + "\""+value+"\""+ ",\n");
        }
        return body.substring(0, body.length() - 2) + "\n}";
    }
}
