package org.wah.doraemon.utils;

import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

/**
 * Gson工具
 */
public class GsonUtils{

    public GsonUtils(){

    }

    /**
     * Long反序列Date
     */
    private static class DateDeserializer implements JsonDeserializer<Date>{

        @Override
        public Date deserialize(JsonElement element, Type typeOf, JsonDeserializationContext context) throws JsonParseException{
            return new Date(element.getAsJsonPrimitive().getAsLong());
        }
    }

    /**
     * Date序列化Long
     */
    private static class DateSerializer implements JsonSerializer<Date>{

        @Override
        public JsonElement serialize(Date date, Type typeOf, JsonSerializationContext context){
            return new JsonPrimitive(date.getTime());
        }
    }

    /**
     * 创建GsonBuilder
     */
    private static GsonBuilder getBuilder(boolean nullable, boolean escapeHtmlChars, boolean longToDate, String datePattern){
        GsonBuilder builder = new GsonBuilder();

        if(nullable){
            builder.serializeNulls();
        }

        if(longToDate){
            builder.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG)
                    .registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        }

        if(!longToDate && !StringUtils.isBlank(datePattern)){
            builder.setDateFormat(datePattern);
        }

        if(!escapeHtmlChars){
            builder.disableHtmlEscaping();
        }

        return builder;
    }

    /**
     * 创建Gson
     */
    public static Gson getGson(boolean nullable, boolean escapeHtmlChars, boolean longToDate, String datePattern){
        return getBuilder(nullable, escapeHtmlChars, longToDate, datePattern).create();
    }

    /**
     * 创建默认Gson
     */
    public static Gson getGson(){
        return getBuilder(false, false, true, null).create();
    }
}
