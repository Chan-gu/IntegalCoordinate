package com.hanuritien.integalparts.coordinate;

import java.util.List;

import org.joda.time.DateTime;

import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;
import com.hanuritien.integalparts.coordinate.model.RLocationVO;
import com.hanuritien.integalparts.coordinate.model.ResultPlaceVO;

/**
 * @author changu
 * 좌표 위치 검출 서비스 
 */
public interface CoordinateService {
	/**
	 * @param targets
	 * 검출좌표 정보 등록/수정
	 */
	void registryCoordinates(List<CoordinatesVO> coordinates);
	
	/**
	 * @param coordinates
	 * 검출좌표 정보 삭제
	 */
	void removeCoordinates(List<CoordinatesVO> coordinates);
	/**
	 * @param rlocation
	 * 위치정보 수신
	 */
	void listenLocation(RLocationVO rlocation);
	/**
	 * @param timeSighting 데이터 발생시간
	 * @param vID 대상 아이디 
	 * @param longitude 경도 x
	 * @param latitude 위도 y
	 * 위치정보 수신
	 */
	void listenLocation(DateTime timeSighting, String vID, float longitude, float latitude);
	
	/**
	 * @param vID
	 * @return
	 * 대상 아이디의 최종 위치정보
	 */
	ResultPlaceVO getTagetPlace(String vID);
}
