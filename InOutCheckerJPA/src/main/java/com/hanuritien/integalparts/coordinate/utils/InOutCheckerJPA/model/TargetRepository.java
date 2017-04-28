package com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value="transactionManagerInOutChecker")
public interface TargetRepository extends JpaRepository<Target, Integer>{

	@Query("SELECT distinct t FROM tbl_targets t left join fetch t.places  WHERE t.vid = :vid")
	Collection<Target> findByVId(@Param("vid")String vid);
	
	@Query("SELECT distinct t FROM tbl_targets t left join fetch t.places  WHERE t.vid = :vid")
	Target findByVIDSingle(@Param("vid")String vid);
	
	@Deprecated
	@Override
	List<Target> findAll();
	
	@Query("SELECT distinct t FROM tbl_targets t left outer join fetch t.places ")
	Collection<Target> getAll();
}
