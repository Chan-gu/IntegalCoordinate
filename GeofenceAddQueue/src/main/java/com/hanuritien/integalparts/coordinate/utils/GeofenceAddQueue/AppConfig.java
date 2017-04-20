package com.hanuritien.integalparts.coordinate.utils.GeofenceAddQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import com.hanuritien.integalparts.coordinate.utils.GeofenceAddQueue.impl.RedisMessagePublisher;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:GeofenceAddQueue.properties")
@ComponentScan("com.hanuritien.integalparts.coordinate.utils.GeofenceAddQueue")
public class AppConfig {
	/**
	 * Rtree상 선 검출 크기(단위 : m)
	 * 선 검출 최대 크기
	 */
	@Value("${Coordinate.Line.Base.BufferSize}")
	public String LINEBUFFSIZE;
	

	/**
	 * Redis 쓰기 주소
	 */
	@Value("${Coordinate.Redis.Write.URLs}")
	public String REDISWRITEURLS;
	
	/**
	 * Redis 쓰기 주소
	 */
	@Value("${Coordinate.Redis.Read.URLs}")
	public String REDISREADURLS;
	
	/**
	 * Redis PoolSize
	 */
	@Value("${Coordinate.Redis.PoolSize}")
	public String REDISPOOLSIZE;
	
	/**
	 * Redis Password
	 */
	@Value("${Coordinate.Redis.Password}")
	public String REDISPASSWORD;
	
	/**
	 * Redis Timeout
	 */
	@Value("${Coordinate.Redis.Timeout}")
	public String REDISTIMEOUT;	
	/**
	 * Redis Channel
	 */
	//@Value("${Coordinate.Redis.Queue.Channel:#{pubsub:queuelocations}}")
	@Value("${Coordinate.Redis.Queue.Channel}")
	public String REDISQUEUECHANNEL;
	
	/**
	 * @return
	 * 읽기/쓰기
	 */
	@Bean
	public JedisConnectionFactory connectionFactory() {
		String[] urls = REDISWRITEURLS.trim().split(":");
				
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName(urls[0]);
		connectionFactory.setPort(Integer.parseInt(urls[1]));
		connectionFactory.setTimeout(Integer.parseInt(REDISTIMEOUT));
		
		if (!REDISPASSWORD.isEmpty())
			connectionFactory.setPassword(REDISPASSWORD);
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(Integer.parseInt(REDISPOOLSIZE));
		connectionFactory.setPoolConfig(poolConfig);
		return connectionFactory;
	}
		
	/**
	 * @return
	 * 읽기 쓰기
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(connectionFactory());
		redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());

		return redisTemplate;
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
