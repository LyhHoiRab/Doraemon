package org.wah.doraemon.utils;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ObjectUtils{

    public ObjectUtils(){

    }

    /**
     * 序列化
     */
    public static String serialize(Object object, boolean nullable, boolean escapeHtmlChars, boolean longToDate, String datePattern){
        Gson gson = GsonUtils.getGson(nullable, escapeHtmlChars, longToDate, datePattern);
        return gson.toJson(object);
    }

    /**
     * 序列化
     */
    public static String serialize(Object object){
        Gson gson = GsonUtils.getGson();
        return gson.toJson(object);
    }

    /**
     * 反序列化
     */
    public static <T> T deserialize(String json, Class<T> clazz, boolean nullable, boolean escapeHtmlChars, boolean longToDate, String datePattern){
        if(clazz == null){
            throw new UtilsException("反序列化对象类型属性不能为空");
        }

        Gson gson = GsonUtils.getGson(nullable, escapeHtmlChars, longToDate, datePattern);
        return gson.fromJson(json, clazz);
    }

    /**
     * 反序列化
     */
    public static <T> T deserialize(String json, Class<T> clazz){
        if(clazz == null){
            throw new UtilsException("反序列化对象类型属性不能为空");
        }

        Gson gson = GsonUtils.getGson();
        return gson.fromJson(json, clazz);
    }

    /**
     * 反序列化
     */
    public static <T> T deserialize(String json, Type type,  boolean nullable, boolean escapeHtmlChars, boolean longToDate, String datePattern){
        if(type == null){
            throw new UtilsException("反序列化对象类型属性不能为空");
        }

        Gson gson = GsonUtils.getGson(nullable, escapeHtmlChars, longToDate, datePattern);
        return gson.fromJson(json, type);
    }

    /**
     * 反序列化
     */
    public static <T> T deserialize(String json, Type type){
        if(type == null){
            throw new UtilsException("反序列化对象类型属性不能为空");
        }

        Gson gson = GsonUtils.getGson();
        return gson.fromJson(json, type);
    }

    /**
     * 去标点符号
     */
    public static String unpunctuation(String json){
        if(StringUtils.isBlank(json)){
            return json;
        }

        return json.replaceAll("\'|\"|\\{|\\}|\\[|\\]", "");
    }
}
