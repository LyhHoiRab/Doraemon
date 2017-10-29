package com.wah.doraemon.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public abstract class Entity implements Serializable{

    private String id;

    public Entity(){

    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public boolean equals(Object object){
        if(this == object){
            return true;
        }

        if(object != null && this.getClass() == object.getClass()){
            Entity entity = (Entity) object;

            if(!StringUtils.isBlank(this.getId())){
                return this.getId().equals(entity.getId());
            }
        }

        return false;
    }

    public int hashCode(){
        return !StringUtils.isBlank(this.getId()) ? this.getId().hashCode() : 0;
    }
}
