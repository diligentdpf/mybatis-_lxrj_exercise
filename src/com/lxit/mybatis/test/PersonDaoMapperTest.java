package com.lxit.mybatis.test;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

import com.lxit.mybatis.Hello;

public class PersonDaoMapperTest {
    static Log log = LogFactory.getLog(Hello.class);
    // 配置驱动类，数据源，用户名，密码
    DataSource dataSource = new PooledDataSource("com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/dpf1", "human", "654321");
    // 配置运行参数
}
