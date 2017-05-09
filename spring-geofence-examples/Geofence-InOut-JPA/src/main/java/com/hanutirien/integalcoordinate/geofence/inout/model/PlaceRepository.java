package com.hanutirien.integalcoordinate.geofence.inout.model;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PlaceRepository extends JpaRepository<Place, Integer>{
	@Query("SELECT distinct t FROM tbl_places t WHERE t.target.id = :targetid AND t.pid = :pid")
	Collection<Place> findByVidPid(@Param("targetid") Integer targetid, @Param("pid")String pid);

	@Modifying
	@Transactional
	@Query("delete from tbl_places t where t.pid = ?1")
    void deletebypid(String pid);
}
