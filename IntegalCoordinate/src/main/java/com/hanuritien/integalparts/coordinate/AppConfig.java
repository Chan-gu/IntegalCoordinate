package com.hanuritien.integalparts.coordinate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
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
@PropertySource("classpath:integalcoordinate.properties")
@ComponentScan("com.hanuritien.integalparts.coordinate")
public class AppConfig {
	
	/**
	 * Rtree상 선 검출 크기(단위 : m)
	 * 선 검출 최대 크기
	 */
	@Value("${Coordinate.Line.Base.BufferSize}")
	public String LINEBUFFSIZE;
	
	/**
	 * Redis 호스트 명칭
	 */
	//@Value("${Coordinate.Redis.URLs:#{localhost:6379}}")
	@Value("${Coordinate.Redis.URLs}")
	public String REDISURLS;
		
	/**
	 * Redis MaxRedirects
	 */
	@Value("${Coordinate.Redis.MaxRedirects}")
	public String REDISMAXREDIRECTS;
	
	/**
	 * Redis PoolSize
	 */
	@Value("${Coordinate.Redis.PoolSize}")
	public String REDISPOOLSIZE;
	
	/**
	 * Redis Channel
	 */
	//@Value("${Coordinate.Redis.Queue.Channel:#{pubsub:queuelocations}}")
	@Value("${Coordinate.Redis.Queue.Channel}")
	public String REDISQUEUECHANNEL;

	@Bean
	public JedisConnectionFactory connectionFactory() {
		String[] urls = REDISURLS.trim().split(",");

		List<String> servers = new ArrayList<String>();
		for (String tmp : urls) {
			if (tmp != null && !tmp.isEmpty()) {
				servers.add(tmp);
			}
		}
		RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(servers);
		clusterConfig.setMaxRedirects(Integer.parseInt(REDISMAXREDIRECTS));
				
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(clusterConfig);
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(Integer.parseInt(REDISPOOLSIZE));
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
        return new ChannelTopic(REDISQUEUECHANNEL);
    }
    
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
