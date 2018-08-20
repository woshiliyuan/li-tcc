package com.li.tcc.demo.dubbo.inventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yuan.li
 */
@SpringBootApplication
@ImportResource({ "classpath:applicationContext.xml" })
@MapperScan("com.li.tcc.demo.dubbo.inventory.mapper")
public class DubboTccInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboTccInventoryApplication.class, args);
	}
}
