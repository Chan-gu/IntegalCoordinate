package com.hanuritien.integalcoordinate.geofencedata.multijpa;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Preconditions;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "com.hanuritien.integalcoordinate.geofencedata.multijpa",
        entityManagerFactoryRef = "coordinateEntityManagerFactory",
        transactionManagerRef = "coordinateTransactionManager")
public class CoordinatesConfig {
    public static final String COORDINATE = "coordinate";

    @Autowired
    private Environment env;
    
    @Bean(name = "coordinateDataSource")
    @Primary
//    @ConfigurationProperties(prefix = "datasource.coordinate")
    public DataSource coordinateDataSource() throws NamingException {
//        return DataSourceBuilder.create().build();
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("datasource.coordinate.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("datasource.coordinate.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("datasource.coordinate.user")));
        if (env.getProperty("datasource.coordinate.pass") != null){
        	dataSource.setPassword(env.getProperty("datasource.coordinate.pass"));
        } else {
        	dataSource.setPassword("");
        }

        return dataSource;
    }

    @Bean(name = "coordinateEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean coordinateEntityManagerFactory(
            EntityManagerFactoryBuilder builder) throws NamingException {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("datasource.coordinate.hbm2ddl"));    	
        properties.put("hibernate.dialect", env.getProperty("datasource.coordinate.database-platform"));    	
        properties.put("hibernate.show_sql", env.getProperty("datasource.coordinate.show_sql"));    	
        return builder
                .dataSource(coordinateDataSource())
                .packages("com.hanuritien.integalcoordinate.geofencedata.multijpa")
                .persistenceUnit(COORDINATE)
                .properties(properties)
                .build();
    }

    @Bean(name = "coordinateTransactionManager")
    @Primary
    public PlatformTransactionManager coordinateTransactionManager() throws NamingException {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(coordinateDataSource());
        jpaTransactionManager.setPersistenceUnitName(COORDINATE);
        return jpaTransactionManager;
    }
    
    
}