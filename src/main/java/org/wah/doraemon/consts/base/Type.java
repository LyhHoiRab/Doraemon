package org.wah.doraemon.consts.base;

/**
 * 常量枚举基础父类
 */
public interface Type{

    int getId();

    String getDescription();

     Enum getById(int id);
}
