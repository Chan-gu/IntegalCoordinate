package com.hanuritien.processor.detection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.AppConfig;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model.Coordinates;
import com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model.CoordinatesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);
	
	private CoordinatesRepository coordinatesRepository;

	@Test
	public void contextLoads1() {
		ApplicationContext applicationContext  = new AnnotationConfigApplicationContext(AppConfig.class);
		coordinatesRepository = applicationContext.getBean(CoordinatesRepository.class);
		
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
