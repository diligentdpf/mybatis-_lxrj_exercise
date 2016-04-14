package com.lxit.mybatis.test;

import java.util.List;

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
 * 使用注解与java代码实现在xml中实现 的东西》HelloMapper.class
 * 
 * @author DPF
 * 
 */
public class HelloMapperTest {
    static Log log = LogFactory.getLog(Hello.class);

    @Test
    public void findHello() {
        // 配置数据源，提供驱动类，用户名，密码
        DataSource dataSource =
                new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/dpf1",
                        "human", "654321");
        // 获取一个事务对象
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        // 运行参数
        Environment environment = new Environment("mybatis", transactionFactory, dataSource);
        // 加载配置文件的参数
        Configuration conf = new Configuration(environment);
        // 加载映射文件
        conf.addMapper(HelloMapper.class);
        // 初始化sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(conf);
        // 获取session对象
        SqlSession session = sqlSessionFactory.openSession();
        // 获取映射实例
        HelloMapper mapper = session.getMapper(HelloMapper.class);
        Hello hello = mapper.findHello(1);
        System.out.println(">>>>>>>>>>>>>>>>>>>");
        System.out.println("编号：》" + hello.getId());
        System.out.println("姓名：》" + hello.getName());
    }

    @Test
    public void findHelloAll() {
        // 配置数据源，提供驱动类，用户名，密码
        DataSource dataSource =
                new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/dpf1",
                        "human", "654321");
        // 获取事务对象工厂对象
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        // 设置运行参数,运行环境对象，
        Environment environment = new Environment("mybatis", transactionFactory, dataSource);
        // 加载配置文件里的参数
        Configuration conf = new Configuration(environment);
        // 加载映射文件xml
        conf.addMapper(HelloMapper.class);
        // 获致session工厂以便创建session
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(conf);
        SqlSession session = sqlSessionFactory.openSession();
        // 获得映射器实例
        HelloMapper helloMapper1 = session.getMapper(HelloMapper.class);
        Hello h1 = new Hello();
        h1.setId(1);
        h1.setName("jack");
        List<Hello> helloList = helloMapper1.findHelloAll(h1);
        for (Hello h : helloList) {
            log.info("编号:>" + h.getId());
            log.info("姓名:>" + h.getName());
        }
    }

    @Test
    public void saveHello() {
        // 配置数据源,给出驱动类，用户名
        DataSource dataSource =
                new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/dpf1",
                        "human", "654321");
        // 创建一个事务对象
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        // 设置运行参数，运行环境对象,三个参数，“mybatis”，transactionFactory,数据源对象
        Environment environment = new Environment("mybatis", transactionFactory, dataSource);
        // 加载配置文件里的参数
        Configuration conf = new Configuration(environment);
        // 加载映射文件
        conf.addMapper(HelloMapper.class);
        // 获取session,但之前要先获取session工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(conf);
        SqlSession session = sf.openSession();
        // 取得映射器实例
        HelloMapper mapper = session.getMapper(HelloMapper.class);
        // 实例化参数对象
        Hello h2 = new Hello();
        h2.setName("王麻子");
        // 调用业务方法
        mapper.saveHello(h2);
        // 提交事务
        session.commit();
        log.info(">>>>>>>>>>>>>>>>>>>");
        log.info("编号：>" + h2.getId());
        log.info("姓名：>" + h2.getName());
    }

    @Test
    public void updateHello() {
        // 配置数据源,给出驱动类，用户名,下面这个类有四个参数，数据库驱动类，数据库源，用户名，密码
        DataSource dataSource =
                new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/dpf1",
                        "human", "654321");
        // 创建事务对象
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        // 配置运行参数,”mybatis“,事务对象，数据源，
        Environment environment = new Environment("mybatis", transactionFactory, dataSource);
        // 加载运行参数
        Configuration conf = new Configuration(environment);
        // 加载映射文件.xml
        conf.addMapper(HelloMapper.class);
        // 获取与数据库会话的session,在这之前先取session工厂实例，工厂实例要通过工厂建造器取得
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(conf);
        SqlSession session = sf.openSession();
        // 取得映射器类的实例
        HelloMapper mapper = session.getMapper(HelloMapper.class);
        // 实例化参数对象
        Hello h3 = new Hello();
        h3.setId(1);
        h3.setName("小六子");
        // 调用业务方法更新
        mapper.updateHello(h3);
        // 提交事务
        session.commit();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:用户信息");
        log.info("编号：>" + h3.getId());
        log.info("姓名：>" + h3.getName());
    }

    @Test
    public void deleteHello() {
        // 配置数据源,给出驱动类，用户名,下面这个类有四个参数，数据库驱动类，数据库源，用户名，密码
        DataSource dataSource =
                new PooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/dpf1",
                        "human", "654321");
        // 创建一个事务对象
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        // 配置运行参数，创建一个环境对象
        Environment environment = new Environment("mybatis", transactionFactory, dataSource);
        // 加载运行参数
        Configuration conf = new Configuration(environment);
        // 加载映射文件
        conf.addMapper(HelloMapper.class);
        // 创建session,创建之前要先得到一个sqlSessionFactory,在这之前还要先通过sqlSessionFactoryBuilder取得前者
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(conf);
        SqlSession session = sf.openSession();
        // 取到映射器实例
        HelloMapper mapper = session.getMapper(HelloMapper.class);
        System.out.println(mapper.deleteHello(1));;
        session.commit();
    }
}
