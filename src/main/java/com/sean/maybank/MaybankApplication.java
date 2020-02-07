package com.sean.maybank;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class MaybankApplication {
	
	@PostConstruct
	  void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(MaybankApplication.class, args);
	}

}
