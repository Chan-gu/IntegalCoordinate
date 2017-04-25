package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
	/**
	 * @return
	 * 미처리 대상 확인
	 */
	@Query("SELECT c FROM tbl_coordinates c LEFT JOIN FETCH c.child s WHERE s.id is null")
	Collection<Coordinates> findNotLoaded();
	
	/**
	 * @param id
	 * @return
	 * 대상 아이디 값으로 검색
	 */
	@Query("SELECT c FROM tbl_coordinates c WHERE c.targetID = ?#{[0]}")
	Collection<Coordinates> findByTargetID(String id);
}
