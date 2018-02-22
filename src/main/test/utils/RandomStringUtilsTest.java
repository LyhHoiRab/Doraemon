package utils;

import org.wah.doraemon.utils.RandomStringUtils;

public class RandomStringUtilsTest{

    public static void main(String[] args){
        int length = 10;
        System.out.println(RandomStringUtils.next(length, false));
        System.out.println(RandomStringUtils.next(length, true));
        System.out.println(RandomStringUtils.nextUpper(length));
        System.out.println(RandomStringUtils.nextLower(length));
        System.out.println(RandomStringUtils.nextLetter(length, false));
        System.out.println(RandomStringUtils.nextLetter(length, true));
        System.out.println(RandomStringUtils.nextNumber(length));
    }
}
