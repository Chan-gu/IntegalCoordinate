package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name = "tbl_coordinates")
public class Coordinates  extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = -7484361818474640499L;

}
