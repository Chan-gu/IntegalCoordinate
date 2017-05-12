package com.hanuritien.integalcoordinate.geofence.impl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DummyBeforeAspect {
	Logger logger = LoggerFactory.getLogger(DummyBeforeAspect.class);
	
	//@Before("execution(* com.hanuritien.integalcoordinate.geofence.impl.dummy.*.*(..))")
	@Before("target(com.hanuritien.integalcoordinate.geofence.InOutListener)")	
	public void DummyCalled(JoinPoint thisJoinPoint) {
		logger.debug("Dummy Service Called!! - " + thisJoinPoint.toShortString());
	}
}
