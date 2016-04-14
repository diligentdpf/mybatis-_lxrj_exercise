package com.lxit.mybatis;

import static org.apache.ibatis.jdbc.SelectBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SelectBuilder.FROM;
import static org.apache.ibatis.jdbc.SelectBuilder.SELECT;
import static org.apache.ibatis.jdbc.SelectBuilder.SQL;
import static org.apache.ibatis.jdbc.SelectBuilder.WHERE;

/**
 * 个人实体sql映射器类
 * 
 * @description 下面使用的方法已经全部过时并废弃，本案例只是拿来测试
 * */
public class PersonDaoProvider {
    /** 指定表名称 */
    private static final String TABLE_NAME = "person";

    // 根据编号查询一个人
    public String findPersonSql(int id) {
        BEGIN();
        SELECT("id,username,password");
        FROM(TABLE_NAME);
        if (id >= 0) {
            WHERE("id=#{id}");
        }
        return SQL();
    }

    // 查询所有个人信息
    public String findPersonAllSql() {
        BEGIN();
        SELECT("id,username,password");
        FROM(TABLE_NAME);
        return SQL();
    }
}
