package com.hanuritien.integalcoordinate.geofence.impl.dummy;

import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.hanuritien.integalcoordinate.geofence.InOutListener;
import com.hanuritien.integalcoordinate.geofence.models.CoordinateInOut;

@Service
public class DummyInOutListener implements InOutListener {
	@Override
	public void actionInOut(CoordinateInOut inout, Collection<String> ids, String vId, DateTime datetime) {
	}

}
