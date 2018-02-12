package org.wah.doraemon.consts;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.consts.base.EnumType;
import org.wah.doraemon.security.exception.UnknownEnumTypeException;

/**
 * 账户状态枚举
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountState implements EnumType{

    NORMAL(0, "正常"),
    FROZEN(1, "冻结"),
    LOCK(2, "锁定"),
    INACTIVITY(3, "未激活");

    //ID
    @Getter
    private int id;
    //描述
    @Getter
    private String description;

    public static AccountState getById(int id){
        for(AccountState state : AccountState.values()){
            if(state.getId() == id){
                return state;
            }
        }

        throw new UnknownEnumTypeException("未知的常量ID[{0}:{1}]", AccountState.class, id);
    }
}
