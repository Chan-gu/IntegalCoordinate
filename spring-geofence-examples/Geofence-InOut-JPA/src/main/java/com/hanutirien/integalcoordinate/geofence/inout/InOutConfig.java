package com.hanutirien.integalcoordinate.geofence.inout;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class InOutConfig {
	
	@Bean
	public StateService stateService() {
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
		//ret.setURL("jdbc:h2:split:24:~/hanuritien/inout;AUTO_SERVER=TRUE;CACHE_SIZE=8192");
		ret.setURL("jdbc:h2:split:24:~/hanuritien/inout;AUTO_SERVER=TRUE");
		ret.setUser("sa");
		return ret;
	}
}
