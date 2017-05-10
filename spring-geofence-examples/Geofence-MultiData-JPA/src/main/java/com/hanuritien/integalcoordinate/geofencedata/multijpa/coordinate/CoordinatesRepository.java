package com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
	/**
	 * @return
	 * 미처리 대상 확인
	 */
	@Query("SELECT distinct c FROM tbl_coordinates c LEFT JOIN FETCH c.child s WHERE s.id is null")
	Collection<Coordinates> findNotLoaded();
	
	/**
	 * @param id
	 * @return
	 * 대상 아이디 값으로 검색
	 */
	@Query("SELECT distinct c FROM tbl_coordinates c WHERE c.targetID = :vid")
	Collection<Coordinates> findByTargetID(@Param("vid")String id);
}
