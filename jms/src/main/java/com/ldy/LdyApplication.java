package com.ldy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ldy.mapper")
public class LdyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdyApplication.class, args);
	}
}
