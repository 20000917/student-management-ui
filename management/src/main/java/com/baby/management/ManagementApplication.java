package com.baby.management;

import cn.ajiehome.common.BaseBeanUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.baby.management.*.mapper")
public class ManagementApplication extends BaseBeanUtils {

    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }

}
