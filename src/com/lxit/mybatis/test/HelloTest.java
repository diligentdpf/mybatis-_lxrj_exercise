package com.lxit.mybatis.test;

import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lxit.mybatis.Hello;

public class HelloTest {
    static Log log = LogFactory.getLog(Hello.class);

    @Test
    public void hello() throws Exception {
        // 加载资源配置文件
        Reader reader = Resources.getResourceAsReader("Configuration.xml");
        // 初始化SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        // 获得session对象
        SqlSession session = factory.openSession();
        System.out.println(session);
        // 执行查询
        Hello hello = session.selectOne("selectHello", 1);
        // 打印结果
        log.info("===========================");
        log.info("编号>:" + hello.getId());
        log.info("姓名>:" + hello.getName());
        session.close();
    }
}
