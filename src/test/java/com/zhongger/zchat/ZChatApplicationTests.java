package com.zhongger.zchat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;


class ZChatApplicationTests {

	@Test
	void contextLoads() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
		System.out.println("格式化输出：" + sdf.format(d));
	}

}
