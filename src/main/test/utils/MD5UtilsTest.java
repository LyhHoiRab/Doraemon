package utils;

import org.wah.doraemon.utils.SHAUtils;

public class MD5UtilsTest{

    public static void main(String[] args){
        String password = "9lab2017";
        String source = "eee36be91cec8a06df57623bff1be928de49ca1f130c4bb4ac5e03e621b99cf0b31a817702b18bb52345fecbbf72d9db5d186fd03cb8fe6d6d48ffaa19bb87ab";

        String target = SHAUtils.bySHA512(password, false);
        System.out.println(target);
        System.out.println(source);

        System.out.println(target.equals(source));
    }
}
