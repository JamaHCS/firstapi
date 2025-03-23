package com.jamadev.firstapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class FirstapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstapiApplication.class, args);
	}

}
