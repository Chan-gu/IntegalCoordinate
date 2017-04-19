package com.hanuritien.integalparts.coordinate.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

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
    
	/* (non-Javadoc)
	 * @see com.hanuritien.integalparts.coordinate.redis.MessagePublisher#publish(java.lang.String)
	 */
	@Override
	public void publish(String message) {
		redisTemplate.convertAndSend(topic.getTopic(), message);
	}

}
