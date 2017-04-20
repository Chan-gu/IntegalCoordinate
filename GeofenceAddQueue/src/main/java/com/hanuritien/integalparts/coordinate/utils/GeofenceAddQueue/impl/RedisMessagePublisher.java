package com.hanuritien.integalparts.coordinate.utils.GeofenceAddQueue.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddQueue.MessagePublisher;

/**
 * @author changu
 * Redis 기반 Pub
 */
@Service
public class RedisMessagePublisher implements MessagePublisher {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
    @Autowired
    private ChannelTopic topic;
	
    public RedisMessagePublisher() {
    }
    
    public RedisMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }    
    
	@Override
	public void publishVO(CoordinatesVO message) {
		redisTemplate.convertAndSend(topic.getTopic(), message);
	}

}
