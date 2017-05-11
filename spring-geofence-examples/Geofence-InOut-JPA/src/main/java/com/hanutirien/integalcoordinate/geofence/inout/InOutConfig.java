package com.hanutirien.integalcoordinate.geofence.inout;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Preconditions;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.hanutirien.integalcoordinate.geofence.inout.model",
        entityManagerFactoryRef = "inoutEntityManagerFactory",
        transactionManagerRef = "inoutTransactionManager")
public class InOutConfig {
	public static final String INOUT = "inout";
	
    @Autowired
    private Environment env;	
	
    @Bean(name = "inoutDataSource")
//    @ConfigurationProperties(prefix = "datasource.inout")
    public DataSource inoutDataSource() {
//        return DataSourceBuilder.create().build();
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("datasource.inout.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("datasource.inout.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("datasource.inout.user")));
        if (env.getProperty("datasource.inout.pass") != null) {
        	dataSource.setPassword(env.getProperty("datasource.inout.pass"));
        }else {
        	dataSource.setPassword("");
        }
        
        return dataSource;    	
    }

    @Bean(name = "inoutEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean inoutEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("datasource.inout.hbm2ddl"));   
        properties.put("hibernate.dialect", env.getProperty("datasource.inout.database-platform"));    	
        properties.put("hibernate.show_sql", env.getProperty("datasource.inout.show_sql"));          
        return builder
                .dataSource(inoutDataSource())
                .packages("com.hanutirien.integalcoordinate.geofence.inout.model")
                .persistenceUnit(INOUT)
                .properties(properties)
                .build();
    }

    @Bean(name = "inoutTransactionManager")
    public PlatformTransactionManager inoutTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(inoutDataSource());
        jpaTransactionManager.setPersistenceUnitName(INOUT);
        return jpaTransactionManager;
    }	
}
