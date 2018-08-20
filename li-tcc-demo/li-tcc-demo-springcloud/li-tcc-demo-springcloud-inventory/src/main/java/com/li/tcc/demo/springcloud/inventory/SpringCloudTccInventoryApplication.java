package com.li.tcc.demo.springcloud.inventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author yuan.li
 */
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableEurekaClient
@EnableFeignClients
@ImportResource({ "classpath:applicationContext.xml" })
@MapperScan("com.li.tcc.demo.springcloud.inventory.mapper")
@EnableTransactionManagement
public class SpringCloudTccInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTccInventoryApplication.class, args);
	}
}
