package com.padeltrophy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties
@ComponentScan({ "com.padeltrophy" })
@EnableAutoConfiguration(exclude = { AopAutoConfiguration.class })
public class PadeltrophyServiceApplication {

	public static void main(String[] args) {
		System.out.println("PadeltrophyServiceApplication:: main");
		SpringApplication.run(PadeltrophyServiceApplication.class, args);
	}
}
