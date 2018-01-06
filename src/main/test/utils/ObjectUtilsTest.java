package utils;

import org.wah.doraemon.utils.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class ObjectUtilsTest{

    public static void main(String[] args){
        String xml = "<xml><test><[!CDATA[test]]></test></xml>";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("test", 1);

        System.out.println(ObjectUtils.serialize(map));
    }
}
