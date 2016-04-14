package com.lxit.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lxit.mybatis.Hello;

/**
 * 使用注解与java代码实现在xml中实现 的东西>>》》》HelloMapperTest.java
 * 
 * @author DPF
 * 
 */
public interface HelloMapper {

    @Select("select * from hello where id=#{id}")
    Hello selectHello(int id);

    @Select("select * from hello where id=#{id}")
    Hello findHello(int id);

    /** 根据用户对象查询所有信息 */
    @Select("select * from Hello where id=#{id} or name=#{name}")
    List<Hello> findHelloAll(Hello hello);

    /** 插入数据 */
    @Select("insert into hello(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void saveHello(Hello hello);

    /** 更新用户 */
    @Update("update hello set name=#{name} where id=#{id}")
    public void updateHello(Hello hello);

    /** 删除用户信息 */
    @Update("delete from hello where id=#{id}")
    public int deleteHello(@Param("id") int id);

}
