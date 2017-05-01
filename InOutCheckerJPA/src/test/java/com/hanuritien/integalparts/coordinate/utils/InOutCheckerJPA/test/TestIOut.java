package com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.test;

import java.util.ArrayList;
import java.util.List;


import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanuritien.integalparts.coordinate.StateService;
import com.hanuritien.integalparts.coordinate.model.InOutPlaceVO;
import com.hanuritien.integalparts.coordinate.model.NowPlaceVO;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.InOutCheckerJPAConfig;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.StateServiceImpl;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.TestConfig;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model.TargetRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class) 
//@Component
public class TestIOut {
	Logger logger = LoggerFactory.getLogger(TestIOut.class);
	
	
	//@Resource(name="stateService")
	StateService ssvc;
	
	//@Autowired
	TargetRepository targetRep;	
	
	
	String vid = "test";
	/**
	 * 출도착 체크
	 */
	@Test
	public void inoutcheck() {
		ApplicationContext applicationContext  = new AnnotationConfigApplicationContext(InOutCheckerJPAConfig.class);
		ssvc = applicationContext.getBean(StateServiceImpl.class);
		
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
		ApplicationContext applicationContext  = new AnnotationConfigApplicationContext(InOutCheckerJPAConfig.class);
		ssvc = applicationContext.getBean(StateServiceImpl.class);		
		NowPlaceVO now = ssvc.getLastPlace(vid);
		logger.debug("state =============================");
		logger.debug(now.getLastTime().toString());
		for (String tmp : now.getPlaces()) {
			logger.debug(tmp);
		}
		logger.debug("===================================");
	}

}
