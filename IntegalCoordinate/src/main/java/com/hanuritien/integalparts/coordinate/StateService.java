package com.hanuritien.integalparts.coordinate;

import java.util.Collection;

import org.joda.time.DateTime;

import com.hanuritien.integalparts.coordinate.model.InOutPlaceVO;
import com.hanuritien.integalparts.coordinate.model.NowPlaceVO;

public interface StateService {
	/**
	 * 검출된 데이터 전달
	 * @param time 시간
	 * @param vId 차량구분자
	 * @param places 현재 검출된 데이터
	 * @return 출도착 응답
	 */
	InOutPlaceVO nowPlace(DateTime time, String vId, Collection<String> places);
	
	/**
	 * 최종 검출 내역  
	 * @param vId 차량구분자
	 * @return 최종 검출정보 
	 */
	NowPlaceVO getLastPlace(String vId);
}
