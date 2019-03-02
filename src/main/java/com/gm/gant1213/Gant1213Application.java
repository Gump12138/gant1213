package com.gm.gant1213;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages ={"com.gm.gant1213.mapper"} )
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass=true)
public class Gant1213Application {

    public static void main(String[] args) {
        SpringApplication.run( Gant1213Application.class, args );
    }

}

