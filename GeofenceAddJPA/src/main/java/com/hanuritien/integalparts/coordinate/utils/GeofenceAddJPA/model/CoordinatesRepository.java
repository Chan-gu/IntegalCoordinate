package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
	@Query("SELECT c FROM tbl_coordinates c LEFT JOIN FETCH c.child s  WHERE s.id is null")
	Collection<Coordinates> findNotLoaded();
}
