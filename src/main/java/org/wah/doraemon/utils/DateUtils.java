package org.wah.doraemon.utils;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
    //毫秒对秒
    private static final long MILLISECOND_SECOND = 1000;
    //毫秒对分钟
    private static final long MILLISECOND_MINUTE = MILLISECOND_SECOND * 60;
    //毫秒对小时
    private static final long MILLISECOND_HOUR = MILLISECOND_MINUTE * 60;
    //毫秒对天
    private static final long MILLISECOND_DAY = MILLISECOND_HOUR * 24;
    //毫秒对周
    private static final long MILLISECOND_WEEK = MILLISECOND_DAY * 7;

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
            throw new UtilsException("无效的时间戳[{0}]", timestamp);
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
            throw new UtilsException("日期[{0}]和模板[{1}]不对应", date, pattern);
        }

        return result;
    }

    /**
     * 解释时间戳
     */
    public static Date parse(Long timestamp){
        if(timestamp == null){
            throw new UtilsException("无效的时间戳[{0}]", timestamp);
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

    /**
     * 当天凌晨
     * 00:00:00.0
     */
    public static Date firstTimeOfDay(Date date){
        if(date == null){
            throw new UtilsException("日期对象不能为空");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 当天深夜
     * 23:59:59.999
     */
    public static Date lastTimeOfDay(Date date){
        if(date == null){
            throw new UtilsException("日期对象不能为空");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    /**
     * 查询日期所在周的第一天
     */
    public static Date firstDayOfWeek(Date date){
        if(date == null){
            throw new UtilsException("日期对象不能为空");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));

        return calendar.getTime();
    }

    /**
     * 查询日期所在周的最后一天
     */
    public static Date lastDayOfWeek(Date date){
        if(date == null){
            throw new UtilsException("日期对象不能为空");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));

        return calendar.getTime();
    }

    /**
     * 查询日期所在月的第一天
     */
    public static Date firstDayOfMonth(Date date){
        if(date == null){
            throw new UtilsException("日期对象不能为空");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    /**
     * 查询日期所在月的最后一天
     */
    public static Date lastDayOfMonth(Date date){
        if(date == null){
            throw new UtilsException("日期对象不能为空");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    /**
     * 查询日期所在年的一天
     */
    public static Date firstDayOfYear(Date date){
        if(date == null){
            throw new UtilsException("日期对象不能为空");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));

        return calendar.getTime();
    }

    /**
     * 查询日期所在年的最后一天
     */
    public static Date lastDayOfYear(Date date){
        if(date == null){
            throw new UtilsException("日期对象不能为空");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));

        return calendar.getTime();
    }

    /**
     * 日期增量算法
     */
    public static Date add(Date date, int field, int amount){
        if(date == null){
            throw new UtilsException("日期对象不能为空");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);

        return calendar.getTime();
    }

    /**
     * 追加毫秒数
     */
    public static Date addMilliseconds(Date date, int amount){
        return add(date, Calendar.MILLISECOND, amount);
    }

    /**
     * 追加秒数
     */
    public static Date addSeconds(Date date, int amount){
        return add(date, Calendar.SECOND, amount);
    }

    /**
     * 追加分钟数
     */
    public static Date addMinutes(Date date, int amount){
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * 追加小时数
     */
    public static Date addHours(Date date, int amount){
        return add(date, Calendar.HOUR, amount);
    }

    /**
     * 追加天数
     */
    public static Date addDays(Date date, int amount){
        return add(date, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * 追加周数
     */
    public static Date addWeeks(Date date, int amount){
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    /**
     * 追加月数
     */
    public static Date addMonths(Date date, int amount){
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * 追加年数
     */
    public static Date addYears(Date date, int amount){
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * 毫秒差
     */
    public static long differenceByMilliseconds(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        return Math.abs(cal_first.getTimeInMillis() - cal_second.getTimeInMillis());
    }

    /**
     * 秒数差
     */
    public static long differenceBySeconds(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        long milliseconds = Math.abs(cal_first.getTimeInMillis() - cal_second.getTimeInMillis());

        return milliseconds / MILLISECOND_SECOND;
    }

    /**
     * 分钟差
     */
    public static long differenceByMinutes(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        long milliseconds = Math.abs(cal_first.getTimeInMillis() - cal_second.getTimeInMillis());

        return milliseconds / MILLISECOND_MINUTE;
    }

    /**
     * 小时差
     */
    public static long differenceByHours(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        long milliseconds = Math.abs(cal_first.getTimeInMillis() - cal_second.getTimeInMillis());

        return milliseconds / MILLISECOND_HOUR;
    }

    /**
     * 天数差
     */
    public static long differenceByDays(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        long milliseconds = Math.abs(cal_first.getTimeInMillis() - cal_second.getTimeInMillis());

        return milliseconds / MILLISECOND_DAY;
    }

    /**
     * 周数差
     */
    public static long differenceByWeeks(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        long milliseconds = Math.abs(cal_first.getTimeInMillis() - cal_second.getTimeInMillis());

        return milliseconds / MILLISECOND_WEEK;
    }

    /**
     * 月数差
     */
    public static long differenceByMonths(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);


        if(first.before(second)){
            return (13 - cal_first.get(Calendar.MONTH)) + cal_second.get(Calendar.MONTH);
        }

        return (13 - cal_second.get(Calendar.MONTH)) + cal_first.get(Calendar.MONTH);
    }

    /**
     * 年数差
     */
    public static long differenceByYears(Date first, Date second){
        if(first == null || second == null){
            throw new UtilsException("比较的日期不能为空");
        }

        Calendar cal_first = Calendar.getInstance();
        cal_first.setTime(first);

        Calendar cal_second = Calendar.getInstance();
        cal_second.setTime(second);

        return Math.abs(cal_first.get(Calendar.YEAR) - cal_second.get(Calendar.YEAR));
    }
}
