package com.hanuritien.integalcoordinate.geofencedata.multijpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanuritien.integalcoordinate.geofence.GeofenceDataService;
import com.hanuritien.integalcoordinate.geofence.models.CoordinatesVO;
import com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate.Coordinates;
import com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate.CoordinatesRepository;
import com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate.LoadedCoordinates;
import com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate.LoadedCoordinatesRepository;
import com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate.RemoveCoordinates;
import com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate.RemoveCoordinatesRepository;

@Primary
@Service
//@Transactional("coordinateTransactionManager")
@Transactional
public class GeofenceDataServiceImpl implements GeofenceDataService {
	Logger logger = LoggerFactory.getLogger(GeofenceDataServiceImpl.class);
	
	@Autowired
	CoordinatesRepository coorRep;

	@Autowired
	LoadedCoordinatesRepository loadRep;

	@Autowired
	RemoveCoordinatesRepository removeRep;

	@Override
	public Collection<CoordinatesVO> getAll() {
		// 기존 처리가 완료된 내역을 반환한다.
		List<CoordinatesVO> ret = new ArrayList<CoordinatesVO>();
		for (LoadedCoordinates tmp : loadRep.findAll()) {
			Coordinates t = tmp.getCoordinates();
			try {
				ret.add(t.toCoordinatesVO());
			} catch (Exception e) {
				logger.error("CoordinateServiceImpl.getAll()", e);
			}
		}

		return ret;
	}

	@Override
	public Collection<CoordinatesVO> getRemove() {
		List<CoordinatesVO> ret = new ArrayList<CoordinatesVO>();
		for (RemoveCoordinates tmp : removeRep.findAll()) {
			Coordinates t = tmp.getCoordinates();
			try {
				if (t != null)
					ret.add(t.toCoordinatesVO());
			} catch (Exception e) {
				logger.error("CoordinateServiceImpl.getRemove()", e);
			}
		}

		return ret;
	}

	@Override
	public Collection<CoordinatesVO> getNews() {
		// 미처리 건에 대해서 반환한다.
		List<CoordinatesVO> ret = new ArrayList<CoordinatesVO>();
		for (Coordinates tmp : coorRep.findNotLoaded()) {
			try {
				ret.add(tmp.toCoordinatesVO());
			} catch (Exception e) {
				logger.error("CoordinateServiceImpl.getNews()", e);
			}
		}

		return ret;
	}

	@Override
	public void doneLoaded(Collection<CoordinatesVO> list) {
		for (CoordinatesVO tmp : list) {
			Coordinates t = coorRep.findOne(tmp.getSaveKey());
			LoadedCoordinates tt = new LoadedCoordinates();
			tt.setCoordinates(t);
			loadRep.save(tt);
		}
		loadRep.flush();
	}
	
	@Override
	public void insertData(Collection<CoordinatesVO> list) {
		for (CoordinatesVO tmp : list) {
			try {
				Coordinates t = new Coordinates(tmp);
				coorRep.save(t);
			} catch (Exception e) {
				logger.error("CoordinateServiceImpl.insertData()", e);
			}
		}
		coorRep.flush();
	}

	@Override
	public void deleteData(Collection<CoordinatesVO> list) {
		for (CoordinatesVO tmp : list) {
			Coordinates t = coorRep.findOne(tmp.getSaveKey());
			RemoveCoordinates tt = new RemoveCoordinates();
			tt.setCoordinates(t);
			removeRep.save(tt);
		}
		removeRep.flush();
	}

	@Override
	public Collection<CoordinatesVO> findById(String id) {
		List<CoordinatesVO> ret = new ArrayList<CoordinatesVO>();
		for (Coordinates t : coorRep.findByTargetID(id)) {
			try {
				ret.add(t.toCoordinatesVO());
			} catch (Exception e) {
				logger.error("CoordinateServiceImpl.findById()", e);
			}
		}
		return ret;
	}

}
