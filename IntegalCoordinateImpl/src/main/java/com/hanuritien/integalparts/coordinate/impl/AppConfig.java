package com.hanuritien.integalparts.coordinate.impl;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hanuritien.integalparts.coordinate.GeofenceService;
import com.hanuritien.integalparts.coordinate.StateService;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.GeofenceAddJPAConfig;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.GeofenceServiceImpl;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.InOutCheckerJPAConfig;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.StateServiceImpl;

//import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:integalcoordinate.properties")
@ComponentScan("com.hanuritien.integalparts.coordinate")
public class AppConfig {
	
	@Bean
	@SuppressWarnings("resource")
	public StateService getStateService() {
		StateService ret = null;
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InOutCheckerJPAConfig.class);
		ret = applicationContext.getBean(StateServiceImpl.class);
		return ret;
	}
	
	@Bean
	@SuppressWarnings("resource")
	public GeofenceService getGeofenceService() {
		GeofenceService ret = null;
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(GeofenceAddJPAConfig.class);
		ret = applicationContext.getBean(GeofenceServiceImpl.class);
		return ret;
	}
    
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
