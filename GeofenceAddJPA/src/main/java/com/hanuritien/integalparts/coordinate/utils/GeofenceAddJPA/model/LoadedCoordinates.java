package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "tbl_loadedcoordinates")
public class LoadedCoordinates extends AbstractPersistable<Integer>{
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "tbl_coordinates_id", referencedColumnName = "id")
	@Setter @Getter
	private Coordinates coordinates;
	
	@Override
	public String toString() {
		return super.toString();
	}
}
