package com.wah.doraemon.commons.security.constants;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public enum UsingState{

    @SerializedName("0")
    NORMAL(0, "正常"),

    @SerializedName("2")
    UNAVAILABLE(2, "不可用"),

    @SerializedName("3")
    LOCKED(3, "锁定"),

    @SerializedName("4")
    FROZEN(4, "冻结");

    private int id;
    private String value;

    private UsingState(int id, String value){
        this.id = id;
        this.value = value;
    }

    public int getId(){
        return id;
    }

    public String getValue(){
        return value;
    }

    public static UsingState getById(int id){
        for(UsingState state : UsingState.values()){
            if(state.getId() == id){
                return state;
            }
        }

        throw new IllegalArgumentException("未知的使用状态常量");
    }

    public static List<UsingState> list(){
        return Arrays.asList(UsingState.values());
    }
}
