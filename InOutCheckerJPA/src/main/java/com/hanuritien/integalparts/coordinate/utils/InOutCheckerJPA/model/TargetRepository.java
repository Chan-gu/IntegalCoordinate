package com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TargetRepository extends JpaRepository<Target, Integer>{

	@Query("SELECT t FROM tbl_targets t JOIN FETCH t.places p WHERE t.vid = :vid")
	Collection<Target> findByVId(@Param("vid")String vid);
	
	@Query("SELECT t FROM tbl_targets t WHERE t.vid = :vid")
	Target findByVIDSingle(@Param("vid")String vid);
}
