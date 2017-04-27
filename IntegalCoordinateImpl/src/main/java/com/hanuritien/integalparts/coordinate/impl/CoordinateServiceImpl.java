/**
 * 
 */
package com.hanuritien.integalparts.coordinate.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.esri.core.geometry.MultiPath;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Point2D;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.hanuritien.integalparts.coordinate.CoordinateService;
import com.hanuritien.integalparts.coordinate.GeofenceService;
import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;
import com.hanuritien.integalparts.coordinate.model.RLocationVO;
import com.hanuritien.integalparts.coordinate.model.ResultPlaceVO;

/**
 * @author changu 좌표 위치 검출 서비스 구현
 */
@Service("coordinateService")
public class CoordinateServiceImpl implements CoordinateService {
	Logger logger = LoggerFactory.getLogger(CoordinateServiceImpl.class);

	RTree<CoordinatesVO, Geometry> tree;

	@Resource(name = "geofenceService")
	GeofenceService geofenceService;

	@PostConstruct
	void initService() {
		tree = loadData();
	}
	
	RTree<CoordinatesVO, Geometry> loadData() {
		RTree<CoordinatesVO, Geometry> tmpTree = RTree.star().create();
		
		Collection<CoordinatesVO> all = geofenceService.getAll();

		for (CoordinatesVO tmp : all) {
			List<Geometry> tlist = getTargetGeometry(tmp);
			for (Geometry tmpgoe : tlist) {
				tmpTree = tmpTree.add(tmp, tmpgoe);
			}
		}
		
		Collection<CoordinatesVO> news = geofenceService.getNews();
		List<CoordinatesVO> dones = new ArrayList<CoordinatesVO>();
		for (CoordinatesVO tmp : news) {
			List<Geometry> tlist = getTargetGeometry(tmp);
			for (Geometry tmpgoe : tlist) {
				tmpTree = tmpTree.add(tmp, tmpgoe);
			}
			dones.add(tmp);
		}
		geofenceService.doneLoaded(dones);
		
		Collection<CoordinatesVO> remove = geofenceService.getRemove();
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

		try {
			switch (arg.getType()) {
			case Circle:
				Point tmpPoint = (Point) arg.getGeometry();
				ret.add(Geometries.circle(tmpPoint.getX(), tmpPoint.getY(), arg.getRadius()));
				break;
			case Line:
				Polyline tmpPoly = (Polyline) arg.getGeometry();
				
				for (int i = 1; i < tmpPoly.getPathCount(); i++) {
					Point2D f = tmpPoly.getXY(i-1);
					Point2D t = tmpPoly.getXY(i);
					
					ret.add(Geometries.line(f.x, f.y, t.x, t.y));
				}
				break;
			case Polygon:
				Polygon tmpPgon = (Polygon) arg.getGeometry();
				
				MultiPath tmpPath = (MultiPath) tmpPgon.getBoundary();
				double top, left, right, bottom;
				double add = 200;
				Point2D t = tmpPath.getXY(0);
				double x = t.x + add;
				double y = t.y + add;
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
				ret.add(Geometries.rectangle(left - add, top - add, right - add, bottom - add));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error("CoordinateServiceImpl.getTargetGeometry()", e);
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hanuritien.integalparts.coordinate.Service#listenLocation(com.
	 * hanuritien.integalparts.coordinate.model.RLocationVO)
	 */
	@Override
	public void listenLocation(RLocationVO rlocation) {
		this.listenLocation(rlocation.getTimeSighting(), rlocation.getVID(), rlocation.getLatitude(), rlocation.getLongitude());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hanuritien.integalparts.coordinate.Service#listenLocation(org.joda.
	 * time.DateTime, java.lang.String, float, float)
	 */
	@Override
	public void listenLocation(DateTime timeSighting, String vID, float longitude, float latitude) {
		// TODO 위치정보 수신

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hanuritien.integalparts.coordinate.Service#getTagetPlace(java.lang.
	 * String)
	 */
	@Override
	public ResultPlaceVO getTagetPlace(String vID) {
		// TODO 대상 아이디의 최종 위치정보
		return null;
	}

	@Override
	public void reloadData() {
		this.initService();
	}

}
