package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "tbl_coordinates")
public class Coordinates extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = -7484361818474640499L;

	@Setter @Getter
	private String targetID;
	@Setter @Getter
	private String type;

	@Lob
	@Setter @Getter
	private String geometry;
	@Setter @Getter
	private Float radius;

	@Temporal(TemporalType.TIMESTAMP)
	@Setter @Getter
	private Date createDate = new Date();
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@Setter @Getter
	private LoadedCoordinates child;
	
	@Override
	public String toString() {
		return super.toString();
	}
}
