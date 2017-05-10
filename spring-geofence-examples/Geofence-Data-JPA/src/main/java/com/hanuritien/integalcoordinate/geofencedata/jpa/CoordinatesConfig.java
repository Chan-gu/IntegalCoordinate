package com.hanuritien.integalcoordinate.geofencedata.jpa;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hanuritien.integalcoordinate.geofence.GeofenceDataService;
import com.hanuritien.integalcoordinate.geofencedata.jpa.coordinate.Coordinates;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "coordinatesEntityManagerFactory", transactionManagerRef = "coordinatesTransactionManager", basePackages = "com.hanuritien.integalcoordinate.geofencedata.jpa.coordinate")
//@PropertySource("classpath:/application.properties")
//@EnableConfigurationProperties(GeofenceDataSourceProperties.class)
public class CoordinatesConfig {

	GeofenceDataSourceProperties ds;
	
	@Bean
	public GeofenceDataService geofenceService(GeofenceDataSourceProperties properties) {
		ds = properties;
		return new GeofenceDataServiceImpl();
	}

	@Bean
	@Primary
	PlatformTransactionManager coordinatesTransactionManager() {
		return new JpaTransactionManager(coordinatesEntityManagerFactory().getObject());
	}

	@Bean
	@Primary
	LocalContainerEntityManagerFactoryBean coordinatesEntityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(coordinatesDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan(Coordinates.class.getPackage().getName());
		factoryBean.setPersistenceUnitName("coordinatesPU");

		return factoryBean;
	}

	// @Value("#{systemProperties['hanuritien.geofencedata.url'] ?:
	// 'jdbc:h2:~/hanuritien/testcoordinate;AUTO_SERVER=TRUE'}")

/*	@Value("${hanuritien.geofencedata.url:@null}")
	String URL;
	@Value("${hanuritien.geofencedata.username:@null}")
	String USERNAME;
	@Value("${hanuritien.geofencedata.password:@null}")
	String PASSWORD;

	@Bean
	@Primary
	DataSource coordinatesDataSource() {
		JdbcDataSource ret = new JdbcDataSource();
		ret.setURL(ds.getUrl());
		ret.setUser(ds.getUsername());
		if (ds.getPassword() != null)
			ret.setPassword(ds.getPassword());
		return ret;
	}*/

	@Value("${hanuritien.geofencedata.url:@null}")
	String URL;
	@Value("${hanuritien.geofencedata.username:@null}")
	String USERNAME;
	@Value("${hanuritien.geofencedata.password:@null}")
	String PASSWORD;
	
	@Bean
	@Primary
	DataSource coordinatesDataSource() {
		JdbcDataSource ret = new JdbcDataSource();
		ret.setURL(URL);
		ret.setUser(USERNAME);
		if (PASSWORD != null)
			ret.setPassword(PASSWORD);
		return ret;
	}
}
