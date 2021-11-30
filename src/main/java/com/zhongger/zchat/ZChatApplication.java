package com.zhongger.zchat;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@MapperScan("com.zhongger.zchat.mapper")
@SpringBootApplication
public class ZChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZChatApplication.class, args);
	}
	@Bean
	public Redisson redisson() {
		// 此为单机模式
		Config config = new Config();
		config.useSingleServer().setAddress("redis://10.93.37.3:6379").setDatabase(0);
		return (Redisson) Redisson.create(config);
	}
//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//
//		};
//	}


}
