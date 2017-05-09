package com.hanuritien.integalcoordinate.geofence;

import org.joda.time.DateTime;

import com.hanuritien.integalcoordinate.geofence.models.RLocationVO;
import com.hanuritien.integalcoordinate.geofence.models.ResultPlaceVO;


/**
 * 좌표 위치 검출 서비스 
 * @author changu
 */
public interface CoordinateService {
	/**
	 * 위치정보 수신
	 * @param rlocation
	 */
	void listenLocation(RLocationVO rlocation);
	/**
	 * 위치정보 수신
	 * @param timeSighting 데이터 발생시간
	 * @param vID 대상 아이디 
	 * @param longitude 경도 x
	 * @param latitude 위도 y
	 */
	void listenLocation(DateTime timeSighting, String vID, float longitude, float latitude);
	
	/**
	 * 대상 아이디의 최종 위치정보
	 * @param vID
	 * @return
	 */
	ResultPlaceVO getTagetPlace(String vID);
	
	/**
	 * 전체 데이터 다시 로드
	 */
	void reloadData();
}
