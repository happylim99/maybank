package com.sean.maybank.config;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public Docket api() { 
		final String swaggerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwiZXhwIjoxNTgxNjc4ODgzfQ.0D_7Cg3-PHLj-upAQGdUtQBN-CF20GZpyq_4u6D5C5-QNP3UdsHdqctsBFTS-SF1jSN6tpVd0mcunK_lAAWyzQ";
        return new Docket(DocumentationType.SWAGGER_2)
        		.globalOperationParameters(Arrays.asList(
                        new ParameterBuilder()
                        .name("Authorization")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .hidden(true)
                        .defaultValue("Bearer " + swaggerToken)
                        .build()))
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.sean.maybank"))              
          .paths(PathSelectors.regex("/.*"))
          .build().apiInfo(apiEndPointsInfo());                                           
    }
	
	private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Maybank Spring Boot")
            .description("Maybank Test")
            .version("1.0.0")
            .build();
    }

}
