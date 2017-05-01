package com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA;

import java.util.HashMap;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("!test")
@Configuration
@PropertySource("classpath:InOutCheckerJPA.properties")
@ComponentScan("com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA")
@EntityScan("com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model")
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages="com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model",
		entityManagerFactoryRef = "inOutCheckerEntityManager", 
		transactionManagerRef = "inOutCheckerTransactionManager")
public class InOutCheckerJPAConfig {
	//private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

	@Autowired
    private Environment env;
	
	@Bean
	@ConfigurationProperties(prefix = "datasource.inoutcheckerjpa")
	public DataSource inOutCheckerDataSource() {
		return DataSourceBuilder.create().build();
	}
/*	@Bean
	public LocalContainerEntityManagerFactoryBean inOutCheckerEntityManagerFactory(EntityManagerFactoryBuilder builder) {

		Map<String, String> propertiesHashMap = new HashMap<String, String>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);
		propertiesHashMap.put("hibernate.hbm2ddl.auto",
		          env.getProperty("datasource.inoutcheckerjpa.hibernate.hbm2ddl.auto"));
		propertiesHashMap.put("hibernate.dialect",
		          env.getProperty("datasource.inoutcheckerjpa.hibernate.dialect"));		

		return builder.dataSource(inOutCheckerDataSource())
				.packages("com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model")
				.properties(propertiesHashMap)
				.build();
	}*/
	
	@PersistenceContext(unitName = "secondary")
    @Bean
    public LocalContainerEntityManagerFactoryBean inOutCheckerEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(inOutCheckerDataSource());
        em.setPackagesToScan(
          new String[] { "com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto",
          env.getProperty("datasource.inoutcheckerjpa.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
          env.getProperty("datasource.inoutcheckerjpa.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
 
        return em;
    }
	
/*	@Bean
	PlatformTransactionManager inOutCheckerTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(inOutCheckerEntityManagerFactory(builder).getObject());
	}*/
	
	@Bean(name="inOutCheckerTransactionManager")
	@Qualifier(value = "inOutCheckerTransactionManager")
    public PlatformTransactionManager inOutCheckerTransactionManager() {
  
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		inOutCheckerEntityManager().getObject());
        return transactionManager;
    }

}
