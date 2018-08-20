package com.li.tcc.demo.dubbo.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yuan.li
 */
@SpringBootApplication
@ImportResource({ "classpath:applicationContext.xml" })
@MapperScan("com.li.tcc.demo.dubbo.order.mapper")
public class DubboTccOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboTccOrderApplication.class, args);
	}

}
