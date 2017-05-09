package com.hanuritien.integalcoordinate.testgeofence;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanuritien.integalcoordinate.geofence.GeofenceDataService;
import com.hanuritien.integalcoordinate.geofence.StateService;
import com.hanuritien.integalcoordinate.geofence.models.CoordinatesVO;
import com.hanuritien.integalcoordinate.geofence.models.InOutPlaceVO;

@RestController
@RequestMapping("test")
public class TestController {
	Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	GeofenceDataService geofenceService;
	
	@Autowired
	StateService stateService;
	
	@RequestMapping(value = "get1", method = RequestMethod.GET)
	public String test() throws Exception {
		for (CoordinatesVO tmp : geofenceService.getAll()) {
			logger.debug( tmp.toString());
		}
		
		return "";
	}
	
	String vid = "test";
	
	@RequestMapping(value = "get2", method = RequestMethod.GET)
	public String test2() throws Exception {
		List<String> pids = new ArrayList<String>();
		pids.add("p1");
		pids.add("p2");
		
		InOutPlaceVO iout = stateService.nowPlace(DateTime.now(), vid, pids, 123.1f, 37.0f);
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
		
		iout = stateService.nowPlace(DateTime.now(), vid, pids, 123.1f, 37.0f);
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
		
		return "";
	}
}
