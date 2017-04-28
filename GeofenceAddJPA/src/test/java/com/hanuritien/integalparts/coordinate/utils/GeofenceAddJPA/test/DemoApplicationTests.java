package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.GeofenceAddJPAConfig;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model.Coordinates;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model.CoordinatesRepository;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model.LoadedCoordinates;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model.LoadedCoordinatesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=GeofenceAddJPAConfig.class) 
@Component
public class DemoApplicationTests {
	Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);
	
	@Autowired 
	private CoordinatesRepository coordinatesRepository;
	
	@Autowired 
	private LoadedCoordinatesRepository loadedCoordinatesRepository;	
	 	
	@Test
	public void contextLoads() {
/*		Coordinates tmp = new Coordinates();
		tmp.setType("circle");
		LoadedCoordinates tt = new LoadedCoordinates();
		tt.setCoordinates(tmp);
		tmp.setChild(tt);
		coordinatesRepository.save(tmp);
		tmp = new Coordinates();
		tmp.setType("circle");
		coordinatesRepository.save(tmp);
		coordinatesRepository.flush();*/
		
/*		for(LoadedCoordinates t : loadedCoordinatesRepository.findAll()) {
			logger.debug( t.toString());
		}
		
		for(Coordinates t : coordinatesRepository.findAll()) {
			logger.debug( t.toString());
		}*/
		
		Coordinates tmp = new Coordinates();
		tmp.setType("circle");
		LoadedCoordinates tt = new LoadedCoordinates();
		tt.setCoordinates(tmp);
		tmp.setChild(tt);
		coordinatesRepository.save(tmp);
		coordinatesRepository.flush();
		
		
		logger.debug( "findNotLoaded 1 ========================>");
		for(LoadedCoordinates t : loadedCoordinatesRepository.findAll()) {
			logger.debug( t.getCoordinates().toString());
		}
		logger.debug( "========================> Endded ");
		
		coordinatesRepository.delete(tmp);
		coordinatesRepository.flush();
		
		logger.debug( "findNotLoaded ========================>");
		for(LoadedCoordinates t : loadedCoordinatesRepository.findAll()) {
			logger.debug(  t.toString());
		}
		logger.debug( "========================> Endded ");
	}

}
