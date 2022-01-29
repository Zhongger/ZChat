package com.zhongger.zchat;

import com.zhongger.zchat.nitty.NettyClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static org.springframework.test.context.transaction.TestTransaction.start;

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
//	static   int a=0;
//	CyclicBarrier cyclicBarrier;
//	Logger logger= LoggerFactory.getLogger(this.getClass());
//	@Test
//	static synchronized void test(){};
//	void  concurrent() throws InterruptedException {
//		LinkedList list=new LinkedList();
//		HashMap<Object,Object>s=new HashMap<>();
//		list.get(2);
//		list.add()
//
//	}

	NettyClient nettyClient=new NettyClient();
	public static void main(String[] args) throws Exception{
		NettyClient nettyClient=new NettyClient();
		nettyClient.stray();
	}

	void client() throws Exception {
		nettyClient.stray();
	}
	@Test
	void client1() throws Exception {
		nettyClient.stray();;
	}
}
