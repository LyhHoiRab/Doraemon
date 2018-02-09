package org.wah.doraemon.utils;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.entity.base.Entity;
import org.wah.doraemon.security.exception.UtilsException;

import java.util.ArrayList;
import java.util.List;

public class EntityUtils{

    public EntityUtils(){

    }

    /**
     * 查询对象列表的ID组
     */
    public static List<String> getIds(List<? extends Entity> list){
        if(list == null){
            throw new UtilsException("对象列表不能为空");
        }

        //结果集
        List<String> ids = new ArrayList<String>();

        for(Entity entity : list){
            String id = entity.getId();

            if(StringUtils.isBlank(id)){
                ids.add(id);
            }
        }

        return ids;
    }
}
