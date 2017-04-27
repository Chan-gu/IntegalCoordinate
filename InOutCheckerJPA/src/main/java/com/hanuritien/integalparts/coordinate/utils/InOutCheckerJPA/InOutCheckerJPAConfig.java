package com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:InOutCheckerJPA.properties")
@ComponentScan("com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA"})
@EnableTransactionManagement
public class InOutCheckerJPAConfig {

}
