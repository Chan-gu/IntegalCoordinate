package com.hanuritien.integalcoordinate.testgeofence;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan (
		basePackages={
				"com.hanuritien.integalcoordinate.testgeofence",
				"com.hanuritien.integalcoordinate.geofencedata.multijpa",
				"com.hanutirien.integalcoordinate.geofence.inout",
				"com.hanuritien.integalcoordinate.geofence.impl"
				}
		)
public class TestGeofenceApplication {

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	public static void main(String[] args) {
		SpringApplication.run(TestGeofenceApplication.class, args);
	}
}
