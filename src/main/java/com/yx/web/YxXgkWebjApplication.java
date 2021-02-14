package com.yx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.yx.web.*")
public class YxXgkWebjApplication {

	public static void main(String[] args) {
		SpringApplication.run(YxXgkWebjApplication.class, args);
	}

}
