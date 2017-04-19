package com.hanuritien.integalparts.coordinate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.hanuritien.integalparts.coordinate.redis.MessagePublisher;
import com.hanuritien.integalparts.coordinate.redis.RedisMessagePublisher;
import com.hanuritien.integalparts.coordinate.redis.RedisMessageSubscriber;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class AppConfig {
	@Autowired
	private Global global;

	@Bean
	public JedisConnectionFactory connectionFactory() {
		String[] urls = global.REDISURLS.trim().split(",");

		List<String> servers = new ArrayList<String>();
		for (String tmp : urls) {
			if (tmp != null && !tmp.isEmpty()) {
				servers.add(tmp);
			}
		}

		RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(servers);
		clusterConfig.setMaxRedirects(global.REDISMAXREDIRECTS);
				
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(clusterConfig);
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(global.REDISPOOLSIZE);
		connectionFactory.setPoolConfig(poolConfig);
		return connectionFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(connectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}
	
	@Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new RedisMessagePublisher(redisTemplate(), topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic(global.REDISQUEUECHANNEL);
    }
}
