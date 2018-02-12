package org.wah.doraemon.consts;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.consts.base.EnumType;
import org.wah.doraemon.security.exception.UnknownEnumTypeException;

/**
 * 使用状态枚举
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UsingState implements EnumType{

    USABLE(0, "可用"),
    DISABLE(1, "不可用");

    //ID
    @Getter
    private int id;
    //描述
    @Getter
    private String description;

    public static UsingState getById(int id){
        for(UsingState state : UsingState.values()){
            if(state.getId() == id){
                return state;
            }
        }

        throw new UnknownEnumTypeException("未知的常量ID[{0}:{1}]", UsingState.class, id);
    }
}
