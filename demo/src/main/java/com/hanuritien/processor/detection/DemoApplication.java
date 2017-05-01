package com.hanuritien.processor.detection;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hanuritien.integalparts.coordinate.CoordinateService;
import com.hanuritien.integalparts.coordinate.GeofenceService;
import com.hanuritien.integalparts.coordinate.StateService;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.GeofenceAddJPAConfig;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.GeofenceServiceImpl;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.InOutCheckerJPAConfig;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.StateServiceImpl;

@Configuration
// @ComponentScan
// @EnableAutoConfiguration (exclude = { DataSourceAutoConfiguration.class })
@EnableAutoConfiguration
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
//@SpringBootApplication
@EntityScan(value = {
		"com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model",
		"com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model" })

@ComponentScan(value = {
		"com.hanuritien.processor.detection",
		"com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model",
		"com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model" })
public class DemoApplication {
	@Bean
	public StateService stateService() {
		StateService ret = null;
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InOutCheckerJPAConfig.class);
		ret = applicationContext.getBean(StateServiceImpl.class);
		return ret;
	}

	/*
	 * @Bean public StateService stateService() { StateService ret = null;
	 * ApplicationContext applicationContext = new
	 * AnnotationConfigApplicationContext(InOutCheckerJPAConfig.class); ret =
	 * applicationContext.getBean(StateServiceImpl.class); return ret; }
	 * 
	 * @Configuration
	 * 
	 * @EnableJpaRepositories(basePackages =
	 * "com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model",
	 * entityManagerFactoryRef = "geofenceEntityManagerFactory",
	 * transactionManagerRef = "geofenceTransactionManager") static class
	 * DbArticleJpaRepositoriesConfig {
	 * 
	 * }
	 * 
	 * @Bean public GeofenceService geofenceService() { GeofenceService ret =
	 * null;
	 * 
	 * ApplicationContext applicationContext = new
	 * AnnotationConfigApplicationContext(GeofenceAddJPAConfig.class); ret =
	 * applicationContext.getBean(GeofenceServiceImpl.class);
	 * 
	 * return ret; }
	 * 
	 * @Configuration
	 * 
	 * @EnableJpaRepositories(basePackages =
	 * "com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model",
	 * entityManagerFactoryRef = "inOutCheckerEntityManager",
	 * transactionManagerRef = "inOutCheckerTransactionManager") static class
	 * DbUserJpaRepositoriesConfig { }
	 */
	private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

	@Autowired
	private Environment env;

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.geofenceaddjpa")
	public DataSource articleDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

		Map<String, String> propertiesHashMap = new HashMap<String, String>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);
		propertiesHashMap.put("hibernate.hbm2ddl.auto",
				env.getProperty("datasource.geofenceaddjpa.hibernate.hbm2ddl.auto"));
		propertiesHashMap.put("hibernate.dialect", env.getProperty("datasource.geofenceaddjpa.hibernate.dialect"));

		return builder.dataSource(articleDataSource())
				.packages("com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model")
				.properties(propertiesHashMap).build();
	}

	@Primary
	@Bean(name = "transactionManager")
	PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactory(builder).getObject());
	}

	@Configuration
	@EnableJpaRepositories(basePackages = "com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
	static class DbArticleJpaRepositoriesConfig {
	}

	@Bean
	@ConfigurationProperties(prefix = "datasource.inoutcheckerjpa")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "entityManagerFactoryUser")
	public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder) {

		Map<String, String> propertiesHashMap = new HashMap<String, String>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);
		propertiesHashMap.put("hibernate.hbm2ddl.auto",
				env.getProperty("datasource.inoutcheckerjpa.hibernate.hbm2ddl.auto"));
		propertiesHashMap.put("hibernate.dialect", env.getProperty("datasource.inoutcheckerjpa.hibernate.dialect"));

		return builder.dataSource(userDataSource())
				.packages("com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model")
				.properties(propertiesHashMap).build();
	}

	@Bean(name = "transactionManagerUser")
	@Primary
	PlatformTransactionManager userTransactionManagerMain(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(userEntityManagerFactory(builder).getObject());
	}

	@Configuration
	@EnableJpaRepositories(basePackages = "com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model", entityManagerFactoryRef = "entityManagerFactoryUser", transactionManagerRef = "transactionManagerUser")
	static class DbUserJpaRepositoriesConfig {
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
