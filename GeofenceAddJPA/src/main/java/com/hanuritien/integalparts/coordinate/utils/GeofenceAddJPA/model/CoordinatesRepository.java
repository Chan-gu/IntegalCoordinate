package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {

}
