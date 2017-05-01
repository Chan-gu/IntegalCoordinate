package com.hanuritien.processor.detection.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanuritien.integalparts.coordinate.StateService;
import com.hanuritien.integalparts.coordinate.model.InOutPlaceVO;

@RestController
@RequestMapping("Greeting")
public class GreetingController {
	Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@Resource(name = "stateService")
	StateService ssvc;
	String vid = "test";

	@RequestMapping("ttt")
	public String helloWorld() {
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
		return "greeting";
	}
}
