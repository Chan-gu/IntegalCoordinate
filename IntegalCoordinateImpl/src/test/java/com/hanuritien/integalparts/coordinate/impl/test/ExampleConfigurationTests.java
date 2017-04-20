package com.hanuritien.integalparts.coordinate.impl.test;


import static org.junit.Assert.*;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanuritien.integalparts.coordinate.CoordinateService;
import com.hanuritien.integalparts.coordinate.impl.AppConfig;
import com.hanuritien.integalparts.coordinate.impl.redis.RedisMessageSubscriber;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleConfigurationTests {
	
	@Resource(name="coordinateService")
	CoordinateService service;

	@Test
	public void testSimpleProperties() throws Exception {
		System.out.println("123213");
		assertNotNull(service);
	}

    @Test
    public void testOnMessage() throws Exception {
        String queue = RedisMessageSubscriber.queue.take();
        System.out.println(queue);
    }
	
}
