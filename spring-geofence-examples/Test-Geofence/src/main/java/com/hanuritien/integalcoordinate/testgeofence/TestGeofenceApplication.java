package com.hanuritien.integalcoordinate.testgeofence;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages={
		"com.hanuritien.integalcoordinate.geofence.inout.model",
		"com.hanuritien.integalcoordinate.geofencedata.multijpa.inoutlistener",
		"com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate",
})
@ComponentScan (
		basePackages={
				"com.hanuritien.integalcoordinate.testgeofence",
				"com.hanuritien.integalcoordinate.geofence.dummy",
				"com.hanuritien.integalcoordinate.geofence.inout",
				"com.hanuritien.integalcoordinate.geofencedata.multijpa",
				"com.hanuritien.integalcoordinate.geofence.impl"
				}
		)
@EnableJpaRepositories(basePackages={
		"com.hanuritien.integalcoordinate.geofence.inout.model",
		"com.hanuritien.integalcoordinate.geofencedata.multijpa.inoutlistener",
		"com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate",	
}) 
@EnableScheduling
@EnableAspectJAutoProxy
@EnableAutoConfiguration
public class TestGeofenceApplication {

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	public static void main(String[] args) {		
/*		System.out.println(GeometryEngine.geodesicDistanceOnWGS84(new Point(127.0f, 45.0f), new Point(127.0f, 45.0001f)));		
		System.out.println(GeometryEngine.geodesicDistanceOnWGS84(new Point(127.0f, 45.0f), new Point(127.0f, 45.00001f)));		
*/		
		SpringApplication.run(TestGeofenceApplication.class, args);
	}
}
