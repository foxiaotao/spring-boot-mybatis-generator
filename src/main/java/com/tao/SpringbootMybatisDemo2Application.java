package com.tao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tao.mapper")
public class SpringbootMybatisDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisDemo2Application.class, args);
	}
}
