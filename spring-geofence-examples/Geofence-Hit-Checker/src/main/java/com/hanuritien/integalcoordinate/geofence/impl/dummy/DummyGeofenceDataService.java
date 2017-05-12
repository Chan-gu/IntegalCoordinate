package com.hanuritien.integalcoordinate.geofence.impl.dummy;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.hanuritien.integalcoordinate.geofence.GeofenceDataService;
import com.hanuritien.integalcoordinate.geofence.models.CoordinatesVO;

@Service
public class DummyGeofenceDataService implements GeofenceDataService {
	
	@Override
	public Collection<CoordinatesVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CoordinatesVO> getRemove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CoordinatesVO> getNews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doneLoaded(Collection<CoordinatesVO> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertData(Collection<CoordinatesVO> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteData(Collection<CoordinatesVO> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<CoordinatesVO> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
