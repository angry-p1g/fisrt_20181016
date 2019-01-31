package com.gq;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 如果mybatis中service实现类中加入事务注解，需要此处添加该注解
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})  //这个注解是排除自动注入 导致mapper注入不进去
@MapperScan({"com.gq.mapper","com.gq.jkl.mapper"})//将项目中对应的mapper类的路径加进来就可以了
public class CourferSbmApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(CourferSbmApplication.class);
        logger.info("启动项目-----start-----");
        SpringApplication.run(CourferSbmApplication.class, args);
        logger.info("启动项目---success--end-----");
    }
}
