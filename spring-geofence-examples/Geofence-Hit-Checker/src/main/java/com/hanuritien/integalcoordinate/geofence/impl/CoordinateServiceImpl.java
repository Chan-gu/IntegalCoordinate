package com.hanuritien.integalcoordinate.geofence.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esri.core.geometry.MultiPath;
import com.esri.core.geometry.OperatorBuffer;
import com.esri.core.geometry.OperatorContains;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Point2D;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.hanuritien.integalcoordinate.geofence.CoordinateService;
import com.hanuritien.integalcoordinate.geofence.GeofenceDataService;
import com.hanuritien.integalcoordinate.geofence.StateService;
import com.hanuritien.integalcoordinate.geofence.models.CoordinateType;
import com.hanuritien.integalcoordinate.geofence.models.CoordinatesVO;
import com.hanuritien.integalcoordinate.geofence.models.NowPlaceVO;
import com.hanuritien.integalcoordinate.geofence.models.RLocationVO;
import com.hanuritien.integalcoordinate.geofence.models.ResultPlaceVO;

import rx.functions.Func1;

import org.springframework.beans.factory.annotation.Autowired;

public class CoordinateServiceImpl implements CoordinateService {
	Logger logger = LoggerFactory.getLogger(CoordinateServiceImpl.class);
	
	RTree<CoordinatesVO, Geometry> tree;
	
	@Autowired
	GeofenceDataService geofenceDataService;	
	
	@Autowired
	StateService stateService;
	
	double add = 200;
	
	@PostConstruct
	void initService() {
		tree = loadData();
	}
	
	RTree<CoordinatesVO, Geometry> loadData() {
		RTree<CoordinatesVO, Geometry> tmpTree = RTree.star().create();
		
		Collection<CoordinatesVO> all = geofenceDataService.getAll();

		for (CoordinatesVO tmp : all) {
			List<Geometry> tlist = getTargetGeometry(tmp);
			for (Geometry tmpgoe : tlist) {
				tmpTree = tmpTree.add(tmp, tmpgoe);
			}
		}
		
		Collection<CoordinatesVO> news = geofenceDataService.getNews();
		List<CoordinatesVO> dones = new ArrayList<CoordinatesVO>();
		for (CoordinatesVO tmp : news) {
			List<Geometry> tlist = getTargetGeometry(tmp);
			for (Geometry tmpgoe : tlist) {
				tmpTree = tmpTree.add(tmp, tmpgoe);
			}
			dones.add(tmp);
		}
		geofenceDataService.doneLoaded(dones);
		
		Collection<CoordinatesVO> remove = geofenceDataService.getRemove();
		for (CoordinatesVO tmp : remove) {
			List<Geometry> tlist = getTargetGeometry(tmp);
			for (Geometry tmpgoe : tlist) {
				tmpTree = tmpTree.delete(tmp, tmpgoe);
			}
			dones.add(tmp);
		}
		
		return tmpTree;
	}	
	
	/**
	 * @param arg
	 * @return 실 검출 형태로 변경
	 */
	List<Geometry> getTargetGeometry(CoordinatesVO arg) {
		List<Geometry> ret = new ArrayList<Geometry>();
		double top, left, right, bottom;
		double x, y;		
		MultiPath tmpPath;
		Point2D t;
		
		try {
			switch (arg.getType()) {
			case Circle:
				Point tmpPoint = (Point) arg.getGeometry().getGeometry();
				x = tmpPoint.getX() + add;
				y = tmpPoint.getY() + add;
				
				ret.add(Geometries.circle(x, y, arg.getRadius()));
				break;
			case Line:
				Polyline tmpPoly = (Polyline) arg.getGeometry().getGeometry();
				tmpPath = (MultiPath)tmpPoly.getBoundary();
				t = tmpPath.getXY(0);
				x = t.x + add;
				y = t.y + add;
				top = y;
				left = x;
				right = x;
				bottom = y;
				double rad = arg.getRadius() / 2 ; 
				
				for (int i = 1; i < tmpPath.getPathCount(); i++) {
					t = tmpPath.getXY(i);
					x = t.x + add;
					y = t.y + add;
					
					if (top > y) top = y;
					if (left > x) left = x;
					if (right < x) right = x;
					if (bottom < y) bottom = y;
				}
				ret.add(Geometries.rectangle(left - rad, top - rad, right + rad, bottom + rad));
				break;
			case Polygon:
				Polygon tmpPgon = (Polygon) arg.getGeometry().getGeometry();
				
				tmpPath = (MultiPath) tmpPgon.getBoundary();
				t = tmpPath.getXY(0);
				x = t.x + add;
				y = t.y + add;
				top = y;
				left = x;
				right = x;
				bottom = y;
				
				for (int i = 1; i < tmpPath.getPathCount(); i++) {
					t = tmpPath.getXY(i);
					x = t.x + add;
					y = t.y + add;
					
					if (top > y) top = y;
					if (left > x) left = x;
					if (right < x) right = x;
					if (bottom < y) bottom = y;
				}
				ret.add(Geometries.rectangle(left, top, right, bottom));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error("CoordinateServiceImpl.getTargetGeometry()", e);
		}

		return ret;
	}	
	
	Collection<CoordinatesVO> searchCoordinatesVO(float x, float y) {
		List<CoordinatesVO> ret = new ArrayList<CoordinatesVO>();
		final com.github.davidmoten.rtree.geometry.Point hitpoint = Geometries.point(x + add,y + add);

		List<Entry<CoordinatesVO, Geometry>> list = tree.search(hitpoint)
				.filter(new Func1<Entry<CoordinatesVO, Geometry>, Boolean>() {

					@Override
					public Boolean call(Entry<CoordinatesVO, Geometry> t) {
						Boolean ret = false;
						com.esri.core.geometry.Point chk = new com.esri.core.geometry.Point(hitpoint.x(), hitpoint.y());
						
						if (t.value().getType() == CoordinateType.Polygon) {
							Polygon p = (Polygon) t.value().getGeometry().getGeometry();
							ret = OperatorContains.local().execute(p, chk, t.value().getGeometry().getSpatialReference(), null);
						} else if (t.value().getType() == CoordinateType.Line) {
							Polyline pl = (Polyline)t.value().getGeometry().getGeometry();
							com.esri.core.geometry.Geometry outputGeom = OperatorBuffer.local().execute(pl, t.value().getGeometry().getSpatialReference(), t.value().getRadius(), null);							
							ret = OperatorContains.local().execute(outputGeom, chk, t.value().getGeometry().getSpatialReference(), null);
						}else {
							ret = true;
						}
						
						return ret;
					}
				})
				.toList().toBlocking().single();
		for (Entry<CoordinatesVO, Geometry> tmp : list) {
			ret.add(tmp.value());
		}
		
		return ret;
	}
	
	@Override
	public void listenLocation(RLocationVO rlocation) {
		this.listenLocation(rlocation.getTimeSighting(), rlocation.getVID(), rlocation.getLatitude(), rlocation.getLongitude());
	}

	@Override
	public void listenLocation(DateTime timeSighting, String vID, float longitude, float latitude) {
		// TODO Auto-generated method stub
		Collection<CoordinatesVO> tmp = searchCoordinatesVO(longitude, latitude);
		List<String> places = new ArrayList<String>();
		for (CoordinatesVO t : tmp) {
			places.add(t.getId());
		}
		logger.debug("listenLocation : " +vID + " find ->" + tmp.size());
		stateService.nowPlace(timeSighting, vID, places, longitude, latitude);
	}

	@Override
	public ResultPlaceVO getTagetPlace(String vID) {
		ResultPlaceVO ret = new ResultPlaceVO();
		NowPlaceVO now = stateService.getLastPlace(vID);
		ret.setLatitude(now.getLatitude());
		ret.setLongitude(now.getLongitude());
		ret.setMatches(now.getPlaces());
		ret.setTimeSighting(now.getLastTime());
		ret.setVID(vID);
		
		return ret;
	}

	@Override
	public void reloadData() {
		tree = loadData();
	}

}
