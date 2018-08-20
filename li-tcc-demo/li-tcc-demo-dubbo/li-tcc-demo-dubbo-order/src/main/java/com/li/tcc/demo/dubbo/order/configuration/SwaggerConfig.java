package com.li.tcc.demo.dubbo.order.configuration;

import static com.google.common.collect.Lists.newArrayList;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yuan.li
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// private static final String SWAGGER_SCAN_BASE_PACKAGE =
	// "com.li.tcc.demo.dubbo.order.controller";
	private static final String VERSION = "1.0.0";

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Swagger API").description("dubbo分布式事务解决方案之TCC测试体验").license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").termsOfServiceUrl("").version(VERSION)
				.contact(new Contact("yuan.li", "", "****@qq.com")).build();
	}

	/**
	 * @SCA Docket：查找@RestController->查找@*Mapping,@ApiOperation
	 * 
	 * @SCA RestController:使用了@Documented注解，使其生成javadoc文档
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				// 查找@RestController
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				// .paths(paths())
				.build()
				// 查找@*Mapping
				.pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.globalResponseMessage(
						RequestMethod.GET,
						newArrayList(new ResponseMessageBuilder().code(500).message("500 message")
								.responseModel(new ModelRef("Error")).build()));
	}

}
