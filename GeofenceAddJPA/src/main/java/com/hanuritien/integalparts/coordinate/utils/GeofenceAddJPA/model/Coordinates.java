package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
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

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Setter @Getter
	private DateTime createDate = DateTime.now();
	
	@OneToOne(cascade = CascadeType.ALL,  optional = true)
	@PrimaryKeyJoinColumn
	@Setter @Getter
	private LoadedCoordinates child;
	
}
