package utils;

import org.wah.doraemon.utils.DateUtils;

import java.util.Date;

public class DateUtilsTest{

    public static void main(String[] args){
//        parse();
//        dayOfWeek();
//        dayOfMonth();
//        dayOfYear();
//        add();

        difference();
    }

    public static void parse(){
        String date = "2017-12-06";
        String pattern = "yyyy-MM-dd HH:mm:ss";

        Date result = DateUtils.parse(date, pattern, true);
        System.out.println(result);
    }

    public static void dayOfWeek(){
        System.out.println(DateUtils.firstDayOfWeek(new Date()));
        System.out.println(DateUtils.lastDayOfWeek(new Date()));
    }

    public static void dayOfMonth(){
        System.out.println(DateUtils.firstDayOfMonth(new Date()));
        System.out.println(DateUtils.lastDayOfMonth(new Date()));
    }

    public static void dayOfYear(){
        System.out.println(DateUtils.firstDayOfYear(new Date()));
        System.out.println(DateUtils.lastDayOfYear(new Date()));
    }

    public static void add(){
        Date date = new Date();

        System.out.println(DateUtils.addMilliseconds(date, 1));
        System.out.println(DateUtils.addSeconds(date, 1));
        System.out.println(DateUtils.addMinutes(date, 1));
        System.out.println(DateUtils.addHours(date, 1));
        System.out.println(DateUtils.addDays(date, 1));
        System.out.println(DateUtils.addWeeks(date, 4));
        System.out.println(DateUtils.addMonths(date, 1));
        System.out.println(DateUtils.addYears(date, 1));
    }

    public static void difference(){
        Date date = new Date();
        Date first = date;
        Date second = DateUtils.addDays(first, 14);

        System.out.println(DateUtils.differenceByMilliseconds(first, second));
        System.out.println(DateUtils.differenceBySeconds(first, second));
        System.out.println(DateUtils.differenceByMinutes(first, second));
        System.out.println(DateUtils.differenceByHours(first, second));
        System.out.println(DateUtils.differenceByDays(first, second));
        System.out.println(DateUtils.differenceByWeeks(first, second));
        System.out.println(DateUtils.differenceByMonths(first, second));
        System.out.println(DateUtils.differenceByYears(first, second));
    }
}
