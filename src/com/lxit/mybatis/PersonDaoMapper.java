package com.lxit.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

public interface PersonDaoMapper {
    /**
     * 根据编号查询每个人
     */
    @SelectProvider(method = "findPersonSql", type = PersonDaoMapper.class)
    @Options(useCache = true, flushCache = false, timeout = 10000)
    @Results(value = {@Result(id = true, property = "id", column = "id", javaType = Integer.class),
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class)})
    public Person findPerson(int id);

    /**
     * 查询所有个人信息
     * */
    @SelectProvider(method = "findPersonAllSql", type = PersonDaoMapper.class)
    @Options(useCache = true, flushCache = false, timeout = 10000)
    @Results(value = {@Result(id = true, property = "id", column = "id", javaType = Integer.class),
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class)})
    public List<Person> findPersonAll();
}
