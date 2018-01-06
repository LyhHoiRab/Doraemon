package org.wah.doraemon.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SortUtils{

    public SortUtils(){

    }

    public static String toString(List list, String separator){
        if(list == null || list.isEmpty()){
            return "";
        }

        Collections.sort(list);

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < list.size(); i++){
            if(i > 0){
                sb.append(separator);
            }

            sb.append(list.get(i));
        }

        return sb.toString();
    }

    public static String toString(Map map, String separator){
        if(map == null || map.isEmpty()){
            return "";
        }

        List keys = new ArrayList(map.keySet());
        Collections.sort(keys);

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < keys.size(); i++){
            if(i > 0){
                sb.append(separator);
            }

            Object key = keys.get(i);
            sb.append(key)
                .append("=")
                .append(map.get(key));
        }

        return sb.toString();
    }
}
