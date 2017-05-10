package com.hanutirien.integalcoordinate.geofence.inout;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("hanuritien.inout")
public class DataSourceProperties {
	@Setter @Getter
	private String url;
	@Setter @Getter
	private String username;
	@Setter @Getter
	private String password;
}
