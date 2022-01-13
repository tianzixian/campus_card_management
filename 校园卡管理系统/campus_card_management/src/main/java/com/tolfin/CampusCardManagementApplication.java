package com.tolfin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.tolfin.web.mapper")
@EnableTransactionManagement
public class CampusCardManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusCardManagementApplication.class, args);
    }

}
