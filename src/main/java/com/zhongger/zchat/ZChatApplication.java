package com.zhongger.zchat;

import com.zhongger.zchat.nitty.NettyServer;
import io.netty.channel.ChannelFuture;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan("com.zhongger.zchat.mapper")
@SpringBootApplication
public class ZChatApplication<NettyServer> implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ZChatApplication.class, args);
	}
	@Bean
	public Redisson redisson() {
		// 此为单机模式
		Config config = new Config();
		config.useSingleServer().setAddress("redis://124.222.21.183:6379").setDatabase(0);
		return (Redisson) Redisson.create(config);
	}
	@Autowired
	com.zhongger.zchat.nitty.NettyServer nettyServer;
	@Override
	public void run(String... args) throws Exception {
		// 开启服务
		ChannelFuture future = nettyServer.start("localhost", 7070);
		// 在JVM销毁前关闭服务
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			@Override
//			public void run() {
//				nettyServer.close();
//			}
//		});
		future.channel().closeFuture().sync();
		System.out.println("关闭服务");
		nettyServer.close();
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
