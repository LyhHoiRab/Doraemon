package org.wah.doraemon.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameUtils{

    public NameUtils(){

    }

    public static String toCamel(String str){
        if(StringUtils.isBlank(str)){
            return str;
        }

        StringBuffer sb = new StringBuffer();

        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(str);

        while(matcher.find()){
            String _str = matcher.group();

            if(matcher.start() == 0){
                sb.append(Character.toLowerCase(_str.charAt(0)));
            }else{
                sb.append(Character.toUpperCase(_str.charAt(0)));
            }

            int index = _str.lastIndexOf("_");
            if(index > 0){
                sb.append(_str.substring(1, index).toLowerCase());
            }else{
                sb.append(_str.substring(1).toLowerCase());
            }
        }

        return sb.toString();
    }

    public static String toUnderLine(String str){
        if(StringUtils.isBlank(str)){
            return str;
        }

        str = String.valueOf(str.charAt(0)).toUpperCase().concat(str.substring(1));

        StringBuffer sb = new StringBuffer();

        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d.]+)?");
        Matcher matcher = pattern.matcher(str);

        while(matcher.find()){
            String _str = matcher.group();
            sb.append(_str.toLowerCase());
            sb.append(matcher.end() == str.length() ? "" : "_");
        }

        return sb.toString();
    }
}
