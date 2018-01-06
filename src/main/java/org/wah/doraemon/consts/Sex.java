package org.wah.doraemon.consts;

import org.wah.doraemon.consts.base.EnumType;
import org.wah.doraemon.security.exception.UnknownEnumTypeException;

/**
 * 用户性别
 */
public enum Sex implements EnumType{

    UNKNOWN(0, "未知"),
    MALE(1, "男性"),
    FEMALE(2, "女性"),
    VAGUE(3, "模糊");

    //ID
    private int id;
    //描述
    private String description;

    private Sex(int id, String description){
        this.id = id;
        this.description = description;
    }

    @Override
    public int getId(){
        return this.id;
    }

    @Override
    public String getDescription(){
        return this.description;
    }

    @Override
    public Sex getById(int id){
        for(Sex sex : Sex.values()){
            if(sex.getId() == id){
                return sex;
            }
        }

        throw new UnknownEnumTypeException("未知的使用状态常量ID[{0}]", id);
    }
}
