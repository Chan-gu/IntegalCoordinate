package com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hanuritien.integalparts.coordinate.StateService;
import com.hanuritien.integalparts.coordinate.model.InOutPlaceVO;
import com.hanuritien.integalparts.coordinate.model.NowPlaceVO;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.InOutCheckerJPAConfig;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model.Target;
import com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model.TargetRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=InOutCheckerJPAConfig.class) 
@Component
public class TestIOut {
	Logger logger = LoggerFactory.getLogger(TestIOut.class);
	
	
	@Resource(name="stateService")
	StateService ssvc;
	
	String vid = "test";
	/**
	 * 출도착 체크
	 */
	@Test
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
	}
	
	@Autowired
	TargetRepository targetRep;
	
	@Test
	public void allData() {
		logger.debug("allData =============================");
		logger.debug("Size : " + targetRep.findAll().size()); 
		for (Target	t : targetRep.findAll()) {
			logger.debug(t.toString());
		}
		logger.debug("===================================");
		
	}

}
