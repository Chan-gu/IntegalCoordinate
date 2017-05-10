package com.hanuritien.integalcoordinate.geofencedata.multijpa.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanuritien.integalcoordinate.geofence.GeofenceDataService;
import com.hanuritien.integalcoordinate.geofence.models.CoordinatesVO;
import com.hanuritien.integalcoordinate.geofencedata.multijpa.CoordinatesConfig;
import com.hanuritien.integalcoordinate.multidatasource.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CoordinatesConfig.class)
public class GeofenceTest {
	Logger logger = LoggerFactory.getLogger(GeofenceTest.class);
	
	@Autowired
	GeofenceDataService geofenceService;
	
	@Test
	public void db1DataSourceTest() {
		for (CoordinatesVO tmp : geofenceService.getAll()) {
			logger.debug( tmp.toString());
		}
	}
}
