package com.hanuritien.integalcoordinate.geofencedata.jpa;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(
		entityManagerFactoryRef = "coordinatesEntityManagerFactory", 
		transactionManagerRef = "coordinatesTransactionManager",
		basePackages="com.hanuritien.integalcoordinate.geofencedata.jpa.coordinate"
		)
public class CoordinatesConfig {

	@Bean
	public GeofenceDataService geofenceService() {
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

	@Bean
	@Primary
	DataSource coordinatesDataSource() {
		JdbcDataSource ret = new JdbcDataSource();
		ret.setURL("jdbc:h2:~/hanuritien/coordinate;AUTO_SERVER=TRUE");
		ret.setUser("sa");
		return ret;
	}
}
