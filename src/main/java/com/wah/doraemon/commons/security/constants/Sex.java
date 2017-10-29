package com.wah.doraemon.commons.security.constants;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public enum Sex{

    @SerializedName("0")
    FEMALE(0, "女性"),

    @SerializedName("1")
    MALE(1, "男性"),

    @SerializedName("2")
    UNKNOWN(2, "未知"),

    @SerializedName("3")
    NEUTRAL(3, "中性"),

    @SerializedName("4")
    MANLY(4, "偏男性"),

    @SerializedName("5")
    WOMANLY(5, "偏女性");

    private int id;
    private String value;

    private Sex(int id, String value){
        this.id = id;
        this.value = value;
    }

    public int getId(){
        return id;
    }

    public String getValue(){
        return value;
    }

    public static Sex getById(int id){
        for(Sex sex : Sex.values()){
            if(sex.getId() == id){
                return sex;
            }
        }

        return UNKNOWN;
    }

    public static List<Sex> list(){
        return Arrays.asList(Sex.values());
    }
}
