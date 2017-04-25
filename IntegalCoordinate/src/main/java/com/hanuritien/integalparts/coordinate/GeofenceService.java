package com.hanuritien.integalparts.coordinate;

import java.util.Collection;

import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;

public interface GeofenceService {
	/**
	 * @return
	 * 전체 데이터 호출
	 */
	Collection<CoordinatesVO> getAll();
	
	/**
	 * @return
	 * 신규 데이터 호출
	 */
	Collection<CoordinatesVO> getNews();
	/**
	 * @param list
	 * 신규 데이터 처리완료
	 */
	void doneLoaded(Collection<CoordinatesVO> list);
	/**
	 * @param list
	 * 신규 펜스 등록
	 */
	void insertData(Collection<CoordinatesVO> list);
	/**
	 * @param list
	 * 기존 펜스 삭제
	 */
	void deleteData(Collection<CoordinatesVO> list);
	/**
	 * @param id
	 * @return
	 * 특정 펜스 찾기
	 */
	Collection<CoordinatesVO> findById(String id);
}
