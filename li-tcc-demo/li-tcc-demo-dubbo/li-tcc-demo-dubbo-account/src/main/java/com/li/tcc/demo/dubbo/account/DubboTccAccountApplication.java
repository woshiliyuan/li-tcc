package com.li.tcc.demo.dubbo.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yuan.li
 */
@SpringBootApplication
@ImportResource({ "classpath:spring-dubbo.xml" })
@MapperScan("com.li.tcc.demo.dubbo.account.mapper")
public class DubboTccAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboTccAccountApplication.class, args);
	}
}