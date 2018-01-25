package org.wah.doraemon.entity.base;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 实体基础父类
 */
public abstract class Entity implements Serializable{

    private transient String id;

    public Entity(){

    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        if(o != null && this.getClass() == o.getClass()){
            Entity entity = (Entity) o;

            if(!StringUtils.isBlank(this.getId())){
                return this.getId().equals(entity.getId());
            }
        }

        return false;
    }

    @Override
    public int hashCode(){
        return !StringUtils.isBlank(this.getId()) ? this.getId().hashCode() : 0;
    }
}
