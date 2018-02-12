package org.wah.doraemon.consts;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.wah.doraemon.consts.base.EnumType;
import org.wah.doraemon.security.exception.UnknownEnumTypeException;

/**
 * 用户性别
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Sex implements EnumType{

    UNKNOWN(0, "未知"),
    MALE(1, "男性"),
    FEMALE(2, "女性"),
    VAGUE(3, "模糊");

    //ID
    @Getter
    private int id;
    //描述
    @Getter
    private String description;
    
    public static Sex getById(int id){
        for(Sex sex : Sex.values()){
            if(sex.getId() == id){
                return sex;
            }
        }

        throw new UnknownEnumTypeException("未知的常量ID[{0}:{1}]", Sex.class, id);
    }
}
