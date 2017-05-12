package com.hanuritien.integalcoordinate.geofence.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hanuritien.integalcoordinate.geofence.CoordinateService;

@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    CoordinateService coordinateService;
    
    @Scheduled(cron="0 */1 2-23 * * *")
    //@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        //log.info("The time is now {}", dateFormat.format(new Date()));
    	coordinateService.loadChangeData();
    }	
}
