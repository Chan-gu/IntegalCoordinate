package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "coordinates") 
@Entity(name = "tbl_loadedcoordinates")
@EqualsAndHashCode(exclude={"coordinates"}, callSuper=false)
public class LoadedCoordinates extends AbstractPersistable<Integer>{
	private static final long serialVersionUID = 5036956919836132856L;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coordinatesID")
	@Setter @Getter
	private Coordinates coordinates;
}
