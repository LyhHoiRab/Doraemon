package org.wah.doraemon.consts;

import org.wah.doraemon.consts.base.EnumType;
import org.wah.doraemon.security.exception.UnknownEnumTypeException;

/**
 * 账户状态枚举
 */
public enum AccountState implements EnumType{

    NORMAL(0, "正常"),
    FROZEN(1, "冻结"),
    LOCK(2, "锁定"),
    INACTIVITY(3, "未激活");

    //ID
    private int id;
    //描述
    private String description;

    private AccountState(int id, String description){
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
    public AccountState getById(int id){
        for(AccountState state : AccountState.values()){
            if(state.getId() == id){
                return state;
            }
        }

        throw new UnknownEnumTypeException("未知的使用状态常量ID[{0}]", id);
    }
}
