package com.hanuritien.integalcoordinate.geofence.impl.dummy;

import java.math.BigDecimal;
import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.hanuritien.integalcoordinate.geofence.StateService;
import com.hanuritien.integalcoordinate.geofence.models.InOutPlaceVO;
import com.hanuritien.integalcoordinate.geofence.models.NowPlaceVO;

@Service
public class DummyStateService implements StateService {

	@Override
	public InOutPlaceVO nowPlace(DateTime time, String vId, Collection<String> places, BigDecimal longitude,
			BigDecimal latitude) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NowPlaceVO getLastPlace(String vId) {
		// TODO Auto-generated method stub
		return null;
	}

}
