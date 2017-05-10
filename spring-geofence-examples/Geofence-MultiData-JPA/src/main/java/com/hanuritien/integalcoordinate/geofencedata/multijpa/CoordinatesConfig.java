package com.hanuritien.integalcoordinate.geofencedata.multijpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate.Coordinates;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan
public class CoordinatesConfig {

    @PersistenceContext
    private EntityManager em;
/*	@Bean
	PlatformTransactionManager coordinatesTransactionManager() {
		return new JpaTransactionManager(coordinatesEntityManagerFactory().getObject());
	}

	@Bean
    @DataSource("coordinates")
	LocalContainerEntityManagerFactoryBean coordinatesEntityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(this.coordinates);
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan(Coordinates.class.getPackage().getName());
		factoryBean.setPersistenceUnitName("coordinatesPU");

		return factoryBean;
	}

    @Autowired 

    private DataSource coordinates;*/
}
