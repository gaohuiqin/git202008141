package com.lanxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2020/8/12 0012.
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lanxin.dao")
public class MainApp {

    public static void main(String[] args) {

        SpringApplication.run(MainApp.class,args);

    }
}
