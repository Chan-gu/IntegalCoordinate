package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:GeofenceAddJPA.properties")
@ComponentScan("com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA"})
@EnableTransactionManagement
public class AppConfig {

}
