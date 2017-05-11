package com.hanuritien.integalcoordinate.testgeofence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esri.core.geometry.MapGeometry;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.hanuritien.integalcoordinate.geofence.CoordinateService;
import com.hanuritien.integalcoordinate.geofence.GeofenceDataService;
import com.hanuritien.integalcoordinate.geofence.StateService;
import com.hanuritien.integalcoordinate.geofence.models.CoordinateType;
import com.hanuritien.integalcoordinate.geofence.models.CoordinatesVO;
import com.hanuritien.integalcoordinate.geofence.models.InOutPlaceVO;
//import com.hanuritien.integalcoordinate.multidatasource.DataSource;

@RestController
@RequestMapping("test")
//@Qualifier("method")
public class TestController {
	Logger logger = LoggerFactory.getLogger(TestController.class);
	

	@Autowired
	StateService stateService;

	@Autowired
	GeofenceDataService geofenceService;	
	
	@Autowired
	CoordinateService coordinateService;
	

	
//	@DataSource("coordinates")
	@RequestMapping(value = "get1", method = RequestMethod.GET)
	public String test() throws Exception {
		for (CoordinatesVO tmp : geofenceService.getAll()) {
			logger.debug( tmp.toString());
		}
		return "";
	}
	
	String vid = "test";
	

//	@DataSource("inout")
	@RequestMapping(value = "get2", method = RequestMethod.GET)
	public String test2() throws Exception {
		List<String> pids = new ArrayList<String>();
		pids.add("p1");
		pids.add("p2");
		
		
		InOutPlaceVO iout = stateService.nowPlace(DateTime.now(), vid, pids, new BigDecimal("123.1"), new BigDecimal("37.0"));
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
	
//	@DataSource("inout")
	@RequestMapping(value = "get3", method = RequestMethod.GET)
	public String test3() throws Exception {
		List<String> pids = new ArrayList<String>();
		pids.add("p2");
		pids.add("p3");
		
		InOutPlaceVO iout = stateService.nowPlace(DateTime.now(), vid, pids, new BigDecimal("123.1"), new BigDecimal("37.0"));
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
	
	@RequestMapping(value = "get4", method = RequestMethod.GET)
	public String test4(String x, String y) throws Exception {
		logger.debug("x    == " + x);
		logger.debug("y    == " + y);
		coordinateService.listenLocation(DateTime.now(), vid, new BigDecimal(x), new BigDecimal(y));

		return "";
	}
	
//	@DataSource("coordinates")
	@RequestMapping(value = "get5", method = RequestMethod.GET)
	public String test5() throws Exception {
		coordinateService.reloadData();

		return "";
	}
	
//	@DataSource("coordinates")
	@RequestMapping(value = "get6", method = RequestMethod.GET)
	public String test6() throws Exception {
		List<CoordinatesVO> test = new ArrayList<CoordinatesVO>();
		CoordinatesVO tmp = new CoordinatesVO();
		tmp.setId("테스트위치");		
		tmp.setRadius(new BigDecimal("100"));
		tmp.setSaveKey(1);
		tmp.setType(CoordinateType.Circle);
		MapGeometry geometry = new MapGeometry(new Point(123.1f, 37.0f), SpatialReference.create(4326));
		tmp.setGeometry(geometry);
		test.add(tmp);
		
		geofenceService.insertData(test);

		return "";
	}
}
