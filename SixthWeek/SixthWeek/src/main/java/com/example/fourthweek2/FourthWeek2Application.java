package com.example.fourthweek2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = "com.example.fourthweek2.**")
public class FourthWeek2Application {

	public static void main(String[] args) {
		SpringApplication.run(FourthWeek2Application.class, args);
	}

}
