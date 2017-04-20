package com.hanuritien.integalparts.coordinate.utils.GeofenceAddQueue.test;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanuritien.integalparts.coordinate.model.CoordinateType;
import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddQueue.AppConfig;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddQueue.impl.RedisMessagePublisher;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleConfigurationTests {
	
    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    @Test
    public void testOnMessage() throws Exception {
        //String message = "Message " + UUID.randomUUID();
    	CoordinatesVO tmp = new CoordinatesVO();
    	tmp.setId("sdasdf");
    	tmp.setType(CoordinateType.Circle);
    	
        redisMessagePublisher.publishVO(tmp);
    }
	
}
