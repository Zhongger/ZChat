package com.zhongger.zchat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//@SpringBootTest
class ZChatApplicationTests {
//	@Autowired
	private StringRedisTemplate stringRedisTemplate;
//	@Test
//	void contextLoads() {
//		List<Integer> list=new ArrayList<>();
//		List<Integer> list1=new ArrayList<>();
//		list.add(1);
//		list.add(2);
//		Integer  nums[]={1,2,3};
//		int[] nums1={1,2,3};
//		 list1= Arrays.asList(nums);
//		System.out.println(list1);
//		System.out.println(Arrays.toString(list.toArray()));
//	}
//	@Test
//	void testredies(){
//
//        stringRedisTemplate.opsForValue().set("21","as asdasd");
//		String result=stringRedisTemplate.opsForValue().get("雷立");
//		stringRedisTemplate.opsForValue().set("user","");
//		System.out.println(result);
//	}
	@Test
	void  concurrent(){
		Thread thread;
		int a=0;
		for(int i =0;i<10;i++){
			thread =new Thread(()->{

				for(int z =0;z<9;z++){
					System.out.print(z);
				}

			});
			thread.start();
		}
	}
}
