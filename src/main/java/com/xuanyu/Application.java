package com.xuanyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用autoconfigure指定SpringBoot应用程序的启动类
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.xuanyu.sw.mapper")
public class Application {
    public static void main(String[] args) {
        /**
         * 使用org.springframework.boot.SpringApplication;
         */
        SpringApplication.run(Application.class, args);
    }
}
