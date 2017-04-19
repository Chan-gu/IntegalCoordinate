package com.hanuritien.integalparts.coordinate.redis;

import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author changu
 * Redis 기반 메세지 수신자
 */
@Service
public class RedisMessageSubscriber implements MessageListener {
	Logger logger = LoggerFactory.getLogger(RedisMessageSubscriber.class);

	public static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	/* (non-Javadoc)
	 * @see org.springframework.data.redis.connection.MessageListener#onMessage(org.springframework.data.redis.connection.Message, byte[])
	 * 메세지 수신
	 */
	@Override
	public void onMessage(Message message, byte[] pattern) {
		if (message != null) {
			logger.debug("Message received: " + new String(message.getBody()));
		}
		
		try {
			queue.put(message.toString());
		} catch (InterruptedException e) {
			logger.error("RedisMessageSubscriber.onMessage", e);
		}
	}

}
