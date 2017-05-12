/**
 * 
 */
package com.hanuritien.integalcoordinate.geofence;

import java.util.Collection;

import org.joda.time.DateTime;

import com.hanuritien.integalcoordinate.geofence.models.CoordinateInOut;

/**
 * 출도착 리스너
 * @author changu
 */
public interface InOutListener {

	/**
	 * @param inout 출/도착 구분
	 * @param ids 검출 대상 아이디
	 * @param vId 차량 구분 정보(차량번호)
	 * @param datetime 관측시간
	 */
	void actionInOut(CoordinateInOut inout, Collection<String> ids, String vId, DateTime datetime);
}
