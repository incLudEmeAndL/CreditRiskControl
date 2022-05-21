package com.chenwei.csust;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableTransactionManagement
@MapperScan("com.chenwei.csust.mapper")
@ComponentScan(basePackages = "com.chenwei.csust.**")
@EnableSwagger2
public class CreditRiskControlApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CreditRiskControlApplication.class, args);
    }
}
