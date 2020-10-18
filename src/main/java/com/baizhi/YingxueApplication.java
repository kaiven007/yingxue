package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.baizhi.dao")
public class YingxueApplication {

    public static void main(String[] args) {

        SpringApplication.run(YingxueApplication.class, args);
    }

}
