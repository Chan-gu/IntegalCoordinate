package com.hanuritien.integalcoordinate.geofencedata.jpa;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("hanuritien.geofencedata")
public class GeofenceDataSourceProperties {
	@Setter @Getter
	private String url;
	@Setter @Getter
	private String username;
	@Setter @Getter
	private String password;
}
