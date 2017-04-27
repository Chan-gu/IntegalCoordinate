package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "coordinates") 
@Entity(name = "tbl_removecoordinates")
@EqualsAndHashCode(exclude={"coordinates"}, callSuper=false)
public class RemoveCoordinates extends AbstractPersistable<Integer>{
	private static final long serialVersionUID = 722847163561972636L;

	@OneToOne
	@JoinColumn(name = "coordinatesID")
	@Setter @Getter
	private Coordinates coordinates;
}
