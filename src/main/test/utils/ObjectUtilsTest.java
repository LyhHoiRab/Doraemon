package utils;

import org.wah.doraemon.entity.User;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.ObjectUtils;

public class ObjectUtilsTest{

    public static void main(String[] args){
        User user_1 = new User();
        user_1.setId(IDGenerator.uuid32());

        User user_2 = new User();
        user_2.setId(IDGenerator.uuid32());

        System.out.println(user_1.equals(user_2));
        System.out.println(user_2.getId());

        System.out.println(ObjectUtils.serialize(user_2));
    }
}
