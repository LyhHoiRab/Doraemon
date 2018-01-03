package utils;

import org.wah.doraemon.utils.DateUtils;

import java.util.Date;

public class DateUtilsTest{

    public static void main(String[] args){
        parse();
    }

    public static void parse(){
        String date = "2017-12-06";
        String pattern = "yyyy-MM-dd HH:mm:ss";

        Date result = DateUtils.parse(date, pattern, true);
        System.out.println(result);
    }
}
