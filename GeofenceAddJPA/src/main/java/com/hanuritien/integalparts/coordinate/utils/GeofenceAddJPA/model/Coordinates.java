package com.hanuritien.integalparts.coordinate.utils.GeofenceAddJPA.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.esri.core.geometry.Geometry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanuritien.integalparts.coordinate.model.CoordinateType;
import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;

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
	
	@OneToOne(cascade = CascadeType.ALL,  optional = true)
	@PrimaryKeyJoinColumn
	@Setter @Getter	
	private RemoveCoordinates rchild;
	
	public Coordinates(){}
	
	public Coordinates(CoordinatesVO arg) throws Exception {
		this.targetID = arg.getId();
		this.type = arg.getType().toValue();
		this.geometry =  new ObjectMapper().writeValueAsString(arg.getGeometry());
		this.radius = arg.getRadius();
	}
	
	/**
	 * @return
	 * @throws Exception
	 * 실 Geofence 객체로 변경
	 */
	public CoordinatesVO toCoordinatesVO() throws Exception {
		CoordinatesVO ret = new CoordinatesVO();
		ret.setSaveKey(this.getId());
		ret.setId(this.targetID);
		ret.setRadius(this.radius);
		ret.setType(CoordinateType.forValue(this.type));
		Geometry tmpGeometry =  new ObjectMapper().readValue(this.geometry, Geometry.class);
		ret.setGeometry(tmpGeometry);
		
		return ret;
	}
	
}
