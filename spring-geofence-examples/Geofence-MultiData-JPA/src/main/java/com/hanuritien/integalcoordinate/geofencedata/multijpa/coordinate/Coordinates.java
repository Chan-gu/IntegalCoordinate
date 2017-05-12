package com.hanuritien.integalcoordinate.geofencedata.multijpa.coordinate;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.MapGeometry;
import com.esri.core.geometry.OperatorExportToJson;
import com.esri.core.geometry.OperatorImportFromJson;
import com.esri.core.geometry.SpatialReference;
import com.hanuritien.integalcoordinate.geofence.models.CoordinateType;
import com.hanuritien.integalcoordinate.geofence.models.CoordinatesVO;

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
	
	@Column(precision = 15, scale = 4)
	@Setter @Getter
	private BigDecimal radius;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Setter @Getter
	private DateTime createDate = DateTime.now(DateTimeZone.forID("Asia/Seoul"));
	
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
		this.geometry = OperatorExportToJson.local().execute(arg.getGeometry().getSpatialReference(), arg.getGeometry().getGeometry());
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
		SpatialReference spatialRef = SpatialReference.create(4326);
		
		if (ret.getType() == CoordinateType.Circle) {
    
			MapGeometry tmpGeometry =  OperatorImportFromJson.local().execute(Geometry.Type.Point, this.geometry);
			ret.setGeometry(tmpGeometry);	
		} else if (ret.getType() == CoordinateType.Line) {
			MapGeometry tmpGeometry = OperatorImportFromJson.local().execute(Geometry.Type.Polyline, this.geometry);
			ret.setGeometry(tmpGeometry);				
		}  else if (ret.getType() == CoordinateType.Polygon) {
			MapGeometry tmpGeometry =  OperatorImportFromJson.local().execute(Geometry.Type.Polygon, this.geometry);
			ret.setGeometry(tmpGeometry);				
		}
		
		
		return ret;
	}
	
}
