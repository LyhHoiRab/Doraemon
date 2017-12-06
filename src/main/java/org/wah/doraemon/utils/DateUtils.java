package org.wah.doraemon.utils;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期相关操作工具
 */
public class DateUtils{

    //默认时间格式化模板
    private static final String DEFAULT_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public DateUtils(){

    }

    /**
     * 格式化日期
     */
    public static String format(Date date, String pattern){
        if(date == null){
            throw new UtilsException("日期对象不能为空");
        }

        if(StringUtils.isBlank(pattern)){
            pattern = DEFAULT_FORMAT_PATTERN;
        }

        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 格式化时间戳
     */
    public static String format(Long timestamp, String pattern){
        if(timestamp == null){
            throw new UtilsException("无效的时间戳 [{0}]", timestamp);
        }

        if(StringUtils.isBlank(pattern)){
            pattern = DEFAULT_FORMAT_PATTERN;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        return formatter.format(new Date(timestamp));
    }

    /**
     * 解释日期字符串
     */
    public static Date parse(String date, String pattern, boolean lenient){
        if(StringUtils.isBlank(date)){
            throw new UtilsException("日期对象不能为空");
        }

        if(StringUtils.isBlank(pattern)){
            throw new UtilsException("日期格式模板不能为空");
        }

        SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.setLenient(lenient);

        final ParsePosition position = new ParsePosition(0);
        String _pattern = pattern;

        if(pattern.endsWith("ZZ")){
            _pattern = _pattern.substring(0, _pattern.length() - 1);
        }

        formatter.applyPattern(_pattern);
        position.setIndex(0);

        String content = date;
        if(pattern.endsWith("ZZ")){
            content = date.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2");
        }

        Date result = formatter.parse(content, position);

        if(result == null || position.getIndex() != content.length()){
            throw new UtilsException("日期 [{0}] 和模板 [{1}] 不对应", date, pattern);
        }

        return result;
    }

    /**
     * 解释时间戳
     */
    public static Date parse(Long timestamp){
        if(timestamp == null){
            throw new UtilsException("无效的时间戳 [{0}]", timestamp);
        }

        return new Date(timestamp);
    }

    /**
     * 判断日期是否同一时刻
     */
    public static boolean isSameTime(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        return first.getTime() == second.getTime();
    }

    /**
     * 判断日期是否同一天
     */
    public static boolean isSameDay(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        return cal_first.get(Calendar.ERA) == cal_second.get(Calendar.ERA)
                && cal_first.get(Calendar.YEAR) == cal_second.get(Calendar.YEAR)
                && cal_first.get(Calendar.DAY_OF_YEAR) == cal_second.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断日期是否同一周
     */
    public static boolean isSameWeek(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        return cal_first.get(Calendar.ERA) == cal_second.get(Calendar.ERA)
                && cal_first.get(Calendar.YEAR) == cal_second.get(Calendar.YEAR)
                && cal_first.get(Calendar.WEEK_OF_YEAR) == cal_second.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 判断日期是否同一月
     */
    public static boolean isSameMonth(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        return cal_first.get(Calendar.ERA) == cal_second.get(Calendar.ERA)
                && cal_first.get(Calendar.YEAR) == cal_second.get(Calendar.YEAR)
                && cal_first.get(Calendar.MONTH) == cal_second.get(Calendar.MONTH);
    }

    /**
     * 判断日期是否同一年
     */
    public static boolean isSameYear(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        return cal_first.get(Calendar.ERA) == cal_second.get(Calendar.ERA)
                && cal_first.get(Calendar.YEAR) == cal_second.get(Calendar.YEAR);
    }

    /**
     * 判断日期是否同一纪元
     */
    public static boolean isSameEra(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        return cal_first.get(Calendar.ERA) == cal_second.get(Calendar.ERA);
    }


}
