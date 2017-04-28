package com.hanuritien.integalparts.coordinate.impl.test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanuritien.integalparts.coordinate.CoordinateService;
import com.hanuritien.integalparts.coordinate.StateService;
import com.hanuritien.integalparts.coordinate.impl.AppConfig;
import com.hanuritien.integalparts.coordinate.model.InOutPlaceVO;
import com.hanuritien.integalparts.coordinate.model.NowPlaceVO;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleConfigurationTests {
	Logger logger = LoggerFactory.getLogger(ExampleConfigurationTests.class);
	
	@Resource(name="coordinateService")
	CoordinateService service;
	
	@Resource(name="stateService")
	StateService ssvc;

	String vid = "test";
	
	@Test
	public void testSimpleProperties() throws Exception {
		System.out.println("123213");
		assertNotNull(service);
	}

/*	@Test
	public void inoutcheck() {
		
		List<String> pids = new ArrayList<String>();
		pids.add("p1");
		pids.add("p2");
		
		InOutPlaceVO iout = ssvc.nowPlace(DateTime.now(), vid, pids);
		logger.debug("in    =============================");
		for (String tmp : iout.getPlaceIns()) {
			logger.debug(tmp);
		}
		logger.debug("===================================");
		
		logger.debug("out    =============================");
		for (String tmp : iout.getPlaceOuts()) {
			logger.debug(tmp);
		}
		logger.debug("===================================");		
		pids.clear();
		pids.add("p2");
		pids.add("p3");
		
		iout = ssvc.nowPlace(DateTime.now(), vid, pids);
		logger.debug("in    =============================");
		for (String tmp : iout.getPlaceIns()) {
			logger.debug(tmp);
		}
		logger.debug("===================================");
		
		logger.debug("out    =============================");
		for (String tmp : iout.getPlaceOuts()) {
			logger.debug(tmp);
		}
		logger.debug("===================================");	
		
	}
	
	@Test
	public void state() {
		NowPlaceVO now = ssvc.getLastPlace(vid);
		logger.debug("state =============================");
		logger.debug(now.getLastTime().toString());
		for (String tmp : now.getPlaces()) {
			logger.debug(tmp);
		}
		logger.debug("===================================");
	}*/
	
}
