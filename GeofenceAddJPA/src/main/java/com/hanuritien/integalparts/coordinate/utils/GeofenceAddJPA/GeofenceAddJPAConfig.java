package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;


//import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:GeofenceAddJPA.properties")
@ComponentScan("com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA")
@EntityScan("com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA"})
@EnableTransactionManagement
public class GeofenceAddJPAConfig {
	private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

    @Autowired
    private Environment env;
	
	@Bean
	@ConfigurationProperties(prefix = "datasource.geofenceaddjpa")
	public DataSource articleDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

		Map<String, String> propertiesHashMap = new HashMap<String, String>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);
		propertiesHashMap.put("hibernate.hbm2ddl.auto",
		          env.getProperty("datasource.geofenceaddjpa.hibernate.hbm2ddl.auto"));
		propertiesHashMap.put("hibernate.dialect",
		          env.getProperty("datasource.geofenceaddjpa.hibernate.dialect"));		

		return builder.dataSource(articleDataSource())
				.packages("com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model")
				.properties(propertiesHashMap)
				.build();
	}

	@Bean(name = "transactionManager")
	PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactory(builder).getObject());
	}

	@Configuration
	@EnableJpaRepositories(basePackages="com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model",
			entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
	static class DbGeofenceAddJPAJpaRepositoriesConfig {
	}

}
