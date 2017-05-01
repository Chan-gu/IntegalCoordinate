package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA;

import java.util.HashMap;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import redis.clients.jedis.JedisPoolConfig;
@Profile("!test")
@Configuration
@EnableTransactionManagement

@PropertySource("classpath:GeofenceAddJPA.properties")
@ComponentScan("com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA")
@EntityScan("com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model")
@EnableJpaRepositories(
		basePackages="com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model",
		entityManagerFactoryRef = "geofenceEntityManagerFactory", 
		transactionManagerRef = "geofenceTransactionManager")
public class GeofenceAddJPAConfig {
	//private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

    @Autowired
    private Environment env;
	
    
    @Primary
 	@Bean
	@ConfigurationProperties(prefix = "datasource.geofenceaddjpa")
	public DataSource GeofenceDataSource() {
		return DataSourceBuilder.create().build();
	}
/*
	@Bean(name = "entityManagerFactoryGeofence")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

		Map<String, String> propertiesHashMap = new HashMap<String, String>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);
		propertiesHashMap.put("hibernate.hbm2ddl.auto",
		          env.getProperty("datasource.geofenceaddjpa.hibernate.hbm2ddl.auto"));
		propertiesHashMap.put("hibernate.dialect",
		          env.getProperty("datasource.geofenceaddjpa.hibernate.dialect"));		

		return builder.dataSource(GeofenceDataSource())
				.packages("com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model")
				.properties(propertiesHashMap)
				.build();
	}

	@Primary
	@Bean(name = "transactionManagerGeofence")
	PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactory(builder).getObject());
	}
*/
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean geofenceEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(GeofenceDataSource());
        em.setPackagesToScan(
          new String[] { "com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto",
          env.getProperty("datasource.geofenceaddjpa.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
          env.getProperty("datasource.geofenceaddjpa.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
 
        return em;
    }
    
    @Primary
	@Bean(name="geofenceTransactionManager")
    public PlatformTransactionManager geofenceTransactionManager() {
  
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		geofenceEntityManagerFactory().getObject());
        return transactionManager;
    }    
}
