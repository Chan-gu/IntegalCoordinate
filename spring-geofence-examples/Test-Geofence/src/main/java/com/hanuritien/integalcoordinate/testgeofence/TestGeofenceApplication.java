package com.hanuritien.integalcoordinate.testgeofence;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hanuritien.integalcoordinate.geofence.GeofenceDataService;
import com.hanuritien.integalcoordinate.geofence.StateService;
import com.hanuritien.integalcoordinate.geofencedata.jpa.CoordinatesConfig;
import com.hanuritien.integalcoordinate.geofencedata.jpa.coordinate.CoordinatesRepository;
import com.hanuritien.integalcoordinate.geofencedata.jpa.coordinate.LoadedCoordinatesRepository;
import com.hanuritien.integalcoordinate.geofencedata.jpa.coordinate.RemoveCoordinatesRepository;
//import com.hanutirien.integalcoordinate.geofence.inout.InOutConfig;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
@Import({ CoordinatesConfig.class })
public class TestGeofenceApplication {

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	final GeofenceDataService geofenceService;

//final StateService stateService;

/*	@Autowired
	public TestGeofenceApplication(GeofenceDataService geofenceService, StateService stateService) {
		this.geofenceService = geofenceService;
		this.stateService = stateService;
	}*/
	
	@Autowired
	public TestGeofenceApplication(GeofenceDataService geofenceService) {
		this.geofenceService = geofenceService;
	}

/*	@Autowired
	public TestGeofenceApplication(GeofenceDataService geofenceService) {
		this.geofenceService = geofenceService;
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(TestGeofenceApplication.class, args);
	}
}
