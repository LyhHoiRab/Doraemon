package org.wah.doraemon.utils;

import eu.bitwalker.useragentutils.*;
import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.security.exception.UtilsException;

/**
 * 浏览器信息分析工具
 */
public class UserAgentUtils{

    public UserAgentUtils(){

    }

    /**
     * 获取客户端信息
     */
    public static UserAgent getUserAgent(String info){
        if(StringUtils.isBlank(info)){
            throw new UtilsException("{0} : 客户端信息不能为空", UserAgentUtils.class);
        }

        return UserAgent.parseUserAgentString(info);
    }

    /**
     * 获取客户端浏览器信息
     */
    public static Browser getBrowser(String info){
        if(StringUtils.isBlank(info)){
            throw new UtilsException("{0} : 客户端信息不能为空", UserAgentUtils.class);
        }

        return UserAgent.parseUserAgentString(info).getBrowser();
    }

    /**
     * 获取客户端系统信息
     */
    public static OperatingSystem getOperatingSystem(String info){
        if(StringUtils.isBlank(info)){
            throw new UtilsException("{0} : 客户端信息不能为空", UserAgentUtils.class);
        }

        return UserAgent.parseUserAgentString(info).getOperatingSystem();
    }

    /**
     * 获取客户端浏览器类型
     */
    public static BrowserType getBrowserType(String info){
        if(StringUtils.isBlank(info)){
            throw new UtilsException("{0} : 客户端信息不能为空", UserAgentUtils.class);
        }

        return UserAgent.parseUserAgentString(info).getBrowser().getBrowserType();
    }

    /**
     * 判断客户端浏览器是否微软厂商
     */
    public static boolean isMicrosoft(String info){
        if(StringUtils.isBlank(info)){
            throw new UtilsException("{0} : 客户端信息不能为空", UserAgentUtils.class);
        }

        Manufacturer manufacturer = UserAgentUtils.getBrowser(info).getManufacturer();

        return Manufacturer.MICROSOFT.equals(manufacturer);
    }

    /**
     * 判断客户端浏览器是否移动设备类型
     */
    public static boolean isMobileDevice(String info){
        if(StringUtils.isBlank(info)){
            throw new UtilsException("{0} : 客户端信息不能为空", UserAgentUtils.class);
        }

        BrowserType type = UserAgent.parseUserAgentString(info).getBrowser().getBrowserType();

        return BrowserType.MOBILE_BROWSER.equals(type) || BrowserType.APP.equals(type);
    }
}
