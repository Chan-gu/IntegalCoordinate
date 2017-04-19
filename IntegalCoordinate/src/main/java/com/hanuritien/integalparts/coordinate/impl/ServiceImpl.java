/**
 * 
 */
package com.hanuritien.integalparts.coordinate.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.hanuritien.integalparts.coordinate.Service;
import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;
import com.hanuritien.integalparts.coordinate.model.RLocationVO;
import com.hanuritien.integalparts.coordinate.model.ResultPlaceVO;

/**
 * @author changu
 * 좌표 위치 검출 서비스 구현
 */
@org.springframework.stereotype.Service("service")
public class ServiceImpl implements Service {
	Logger logger = LoggerFactory.getLogger(ServiceImpl.class);
	
	RTree<CoordinatesVO, Geometry> tree;

	/* (non-Javadoc)
	 * @see com.hanuritien.integalparts.coordinate.Service#registryCoordinates(java.util.List)
	 */
	@Override
	public void registryCoordinates(List<CoordinatesVO> coordinates) {
		// TODO 검출좌표 정보 등록/수정

	}

	/* (non-Javadoc)
	 * @see com.hanuritien.integalparts.coordinate.Service#listenLocation(com.hanuritien.integalparts.coordinate.model.RLocationVO)
	 */
	@Override
	public void listenLocation(RLocationVO rlocation) {
		// TODO 위치정보 수신 객체

	}

	/* (non-Javadoc)
	 * @see com.hanuritien.integalparts.coordinate.Service#listenLocation(org.joda.time.DateTime, java.lang.String, float, float)
	 */
	@Override
	public void listenLocation(DateTime timeSighting, String vID, float longitude, float latitude) {
		// TODO 위치정보 수신

	}

	/* (non-Javadoc)
	 * @see com.hanuritien.integalparts.coordinate.Service#getTagetPlace(java.lang.String)
	 */
	@Override
	public ResultPlaceVO getTagetPlace(String vID) {
		// TODO 대상 아이디의 최종 위치정보
		return null;
	}

	@Override
	public void removeCoordinates(List<CoordinatesVO> coordinates) {
		// TODO 검출좌표 정보 삭제
		
	}

}
