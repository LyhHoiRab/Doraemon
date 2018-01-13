package utils;

import org.wah.doraemon.entity.Account;
import org.wah.doraemon.utils.ObjectUtils;

public class ObjectUtilsTest{

    public static void main(String[] args){
        Account account = new Account();
        account.setId("1");

        String json = ObjectUtils.serialize(account);
        System.out.println(ObjectUtils.deserialize(json, new Account()));
    }
}
