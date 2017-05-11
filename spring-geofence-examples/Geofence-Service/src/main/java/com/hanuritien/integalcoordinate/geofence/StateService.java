package com.hanuritien.integalcoordinate.geofence;

import java.math.BigDecimal;
import java.util.Collection;
import org.joda.time.DateTime;
import com.hanuritien.integalcoordinate.geofence.models.InOutPlaceVO;
import com.hanuritien.integalcoordinate.geofence.models.NowPlaceVO;


public interface StateService {
	/**
	 * @param time 시간
	 * @param vId 차량구분자
	 * @param places 현재 검출된 데이터
	 * @param longitude 경도
	 * @param latitude 위도
	 * @return 출도착 응답
	 */
	InOutPlaceVO nowPlace(DateTime time, String vId, Collection<String> places, BigDecimal longitude, BigDecimal latitude);
	
	/**
	 * 최종 검출 내역  
	 * @param vId 차량구분자
	 * @return 최종 검출정보 
	 */
	NowPlaceVO getLastPlace(String vId);
}
