package com.hanuritien.processor.detection;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hanuritien.integalparts.coordinate.StateService;
import com.hanuritien.integalparts.coordinate.model.InOutPlaceVO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

	
	Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

	/*
	@AutoConfigureAfter
	CoordinatesRepository coordinatesRepository;

	@Test
	public void contextLoads1() {
		ApplicationContext applicationContext  = new AnnotationConfigApplicationContext(GeofenceAddJPAConfig.class);
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
	*/
	
	
	@Resource(name="stateService")
	StateService ssvc;
	
	String vid = "test";
	
	@Test
	public void inoutcheck() {
	
		List<String> pids = new ArrayList<String>();
		pids.add("p1");
		pids.add("p2");
		
		InOutPlaceVO iout = ssvc.nowPlace(DateTime.now(), vid, pids);
		logger.info("in    =============================");
		for (String tmp : iout.getPlaceIns()) {
			logger.info(tmp);
		}
		logger.info("===================================");
		
		logger.info("out    =============================");
		for (String tmp : iout.getPlaceOuts()) {
			logger.info(tmp);
		}
		logger.info("===================================");		
		pids.clear();
		pids.add("p2");
		pids.add("p3");
		
		iout = ssvc.nowPlace(DateTime.now(), vid, pids);
		logger.info("in    =============================");
		for (String tmp : iout.getPlaceIns()) {
			logger.info(tmp);
		}
		logger.info("===================================");
		
		logger.info("out    =============================");
		for (String tmp : iout.getPlaceOuts()) {
			logger.info(tmp);
		}
		logger.info("===================================");	
		
	}
}
