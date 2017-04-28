package com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value="transactionManagerInOutChecker")
public interface PlaceRepository extends JpaRepository<Place, Integer>{
	@Query("SELECT t FROM tbl_places t INNER JOIN t.target p WHERE p.id = :targetid AND t.pid = :pid")
	Collection<Place> findByVidPid(@Param("targetid") Integer targetid, @Param("pid")String pid);
}
