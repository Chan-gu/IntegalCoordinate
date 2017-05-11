package com.hanuritien.integalcoordinate.testgeofence;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;

@SpringBootApplication
@ComponentScan (
		basePackages={
				"com.hanuritien.integalcoordinate.testgeofence",
				"com.hanuritien.integalcoordinate.geofencedata.multijpa",
				"com.hanutirien.integalcoordinate.geofence.inout",
				"com.hanuritien.integalcoordinate.geofence.impl"
				}
		)
@EnableScheduling
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
