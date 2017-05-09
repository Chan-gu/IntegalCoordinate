package com.hanuritien.integalcoordinate.geofencedata.jpa.test;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.OperatorExportToJson;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanuritien.integalcoordinate.geofence.models.CoordinatesVO;
import com.hanuritien.integalcoordinate.geofencedata.jpa.CoordinatesConfig;
import com.hanuritien.integalcoordinate.geofencedata.jpa.coordinate.Coordinates;
import com.hanuritien.integalcoordinate.geofencedata.jpa.coordinate.CoordinatesRepository;
import com.hanuritien.integalcoordinate.geofencedata.jpa.coordinate.LoadedCoordinates;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CoordinatesConfig.class)
@Component
public class GeofenceDataTest {
	Logger logger = LoggerFactory.getLogger(GeofenceDataTest.class);
	
	@Autowired CoordinatesRepository repository;
	@Autowired @Qualifier("coordinatesEntityManagerFactory") EntityManager em;	
	
	@Test
	public void contextLoads() throws JsonProcessingException {
		Coordinates tmp = new Coordinates();
		tmp.setType("circle");
		tmp.setTargetID("234");
		Point pt = new Point(-106.4453583, 39.11775);	
		tmp.setGeometry(OperatorExportToJson.local().execute(SpatialReference.create(4326),pt));
		LoadedCoordinates tt = new LoadedCoordinates();
		tt.setCoordinates(tmp);
		tmp.setChild(tt);
		repository.save(tmp);
		repository.flush();
		
		
		logger.debug( "findNotLoaded 1 ========================>");
		for(Coordinates t : repository.findAll()) {
			logger.debug( t.toString());
		}
		logger.debug( "========================> Endded ");
		//repository.deleteAll();
		//repository.delete(tmp);
		//repository.flush();
		
		logger.debug( "findNotLoaded ========================>");
		for(Coordinates t : repository.findAll()) {
			logger.debug(  t.toString());
		}
		logger.debug( "========================> Endded ");		
	}
}
