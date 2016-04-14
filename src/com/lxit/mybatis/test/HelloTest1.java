package com.lxit.mybatis.test;


import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import com.lxit.mybatis.Hello;
import com.lxit.mybatis.mapper.HelloMapper;

/**
 * @author DPF
 * @description 使用java代码实现用xml实现的东西
 */
public class HelloTest1 {
    static Log log = LogFactory.getLog(Hello.class);

    @Test
    public void hello2() throws Exception {
        // 用构造方法配置数据源，驱动类，用户名，密码
        DataSource dataSource =
                new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/dpf1",
                        "human", "654321");
        // 实例化事务管理器
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        // 运行配置参数
        Environment environment = new Environment("mybatis", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        // 动态加载映射类
        configuration.addMapper(HelloMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        // 获取session对象
        SqlSession session = sqlSessionFactory.openSession();
        // 映射器类查询方法调用
        HelloMapper service = session.getMapper(HelloMapper.class);
        // 获得返回结果
        Hello hello = service.selectHello(2);
        // 打印结果
        log.info("===========================");
        log.info("编号>:" + hello.getId());
        log.info("姓名>:" + hello.getName());
        session.close();
    }
}
