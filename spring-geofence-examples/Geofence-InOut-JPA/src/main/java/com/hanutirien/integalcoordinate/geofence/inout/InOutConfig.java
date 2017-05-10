package com.hanutirien.integalcoordinate.geofence.inout;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

import com.hanuritien.integalcoordinate.geofence.StateService;
import com.hanutirien.integalcoordinate.geofence.inout.model.Target;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "inOutEntityManagerFactory", 
		transactionManagerRef = "inOutTransactionManager",
		basePackages="com.hanutirien.integalcoordinate.geofence.inout.model")
@EnableConfigurationProperties(DataSourceProperties.class)
public class InOutConfig {
	DataSourceProperties ds;
	
	@Bean
	public StateService stateService(DataSourceProperties properties) {
		this.ds = properties;
		return new StateServiceImpl();
	}
	
	@Bean
	PlatformTransactionManager inOutTransactionManager() {
		return new JpaTransactionManager(inOutEntityManagerFactory().getObject());
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean inOutEntityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(inOutDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan(Target.class.getPackage().getName());
		factoryBean.setPersistenceUnitName("inOutPU");

		return factoryBean;
	}

	@Bean
	DataSource inOutDataSource() {
		JdbcDataSource ret = new JdbcDataSource();
		ret.setURL(ds.getUrl());
		ret.setUser(ds.getUsername());
		if (ds.getPassword() != null)
			ret.setPassword(ds.getPassword());
		return ret;
	}
	
}
