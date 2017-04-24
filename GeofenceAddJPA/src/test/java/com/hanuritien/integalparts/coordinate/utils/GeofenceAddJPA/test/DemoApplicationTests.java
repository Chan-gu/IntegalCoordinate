package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.AppConfig;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model.Coordinates;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model.CoordinatesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class) 
@Component
public class DemoApplicationTests {
	Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);
	
	@Autowired 
	private CoordinatesRepository coordinatesRepository;
	 	
	@Test
	public void contextLoads() {
		Coordinates tmp = new Coordinates();
		tmp.setType("circle");
		coordinatesRepository.save(tmp);
		tmp = new Coordinates();
		tmp.setType("circle");
		coordinatesRepository.save(tmp);
		coordinatesRepository.flush();
		
		for(Coordinates t : coordinatesRepository.findAll()) {
			logger.debug("Asdf", t.toString());
		}
	}

}
