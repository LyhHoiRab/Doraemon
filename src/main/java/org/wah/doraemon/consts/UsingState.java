package org.wah.doraemon.consts;

import org.wah.doraemon.consts.base.Type;
import org.wah.doraemon.security.exception.UnknownEnumTypeException;

/**
 * 使用状态枚举
 */
public enum UsingState implements Type{

    USABLE(0, "可用"),
    DISABLE(1, "不可用");

    //ID
    private int id;
    //描述
    private String description;

    private UsingState(int id, String description){
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
    public UsingState getById(int id){
        for(UsingState state : UsingState.values()){
            if(state.getId() == id){
                return state;
            }
        }

        throw new UnknownEnumTypeException("未知的使用状态常量ID : {0}", id);
    }
}