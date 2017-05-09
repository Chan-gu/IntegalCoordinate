package com.hanuritien.integalcoordinate.geofence;

import java.util.Collection;

import com.hanuritien.integalcoordinate.geofence.models.CoordinatesVO;

public interface GeofenceDataService {
	/**
	 * 전체 데이터 호출
	 * @return
	 */
	Collection<CoordinatesVO> getAll();
	
	/**
	 * 삭제 데이터 호출
	 * @return
	 */
	Collection<CoordinatesVO> getRemove();
	
	/**
	 * 신규 데이터 호출
	 * @return
	 */
	Collection<CoordinatesVO> getNews();
	/**
	 * 신규 데이터 처리완료
	 * @param list
	 */
	void doneLoaded(Collection<CoordinatesVO> list);
	/**
	 * 신규 펜스 등록	 * 
	 * @param list
	 */
	void insertData(Collection<CoordinatesVO> list);
	/**
	 * 기존 펜스 삭제
	 * @param list
	 */
	void deleteData(Collection<CoordinatesVO> list);
	/**
	 * 특정 펜스 찾기
	 * @param id
	 * @return
	 */
	Collection<CoordinatesVO> findById(String id);
}
