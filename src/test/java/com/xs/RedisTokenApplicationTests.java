package com.xs;

import com.xs.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTokenApplicationTests {

	@Autowired
	RedisService redisService;
	@Test
	public void contextLoads() {
		redisService.set("1","xs");

	}

}
