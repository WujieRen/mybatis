package com.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * Created by renwujie on 2018/05/14 at 13:03
 */
public interface UserMapper {
    //1.直接和.xml文件对应
    public int getUserCount();
    //2.用注解的方式，这样就可以不用.xml，但只支持较简单的sql
    @Select("select count(1) from user")
    public int getUserCountByAnnotion();
}
