package com.hanuritien.integalcoordinate.geofence.inout.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity(name = "tbl_targets")
public class Target extends AbstractPersistable<Integer>{
	private static final long serialVersionUID = 6198854688812727055L;

	@Column(unique=true)
	@Setter @Getter
	private String vid;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Setter @Getter	
	private DateTime lastDate;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="target", fetch=FetchType.EAGER)
	@Setter @Getter	
	private Set<Place> places;
	
	/**
	 * 경도 x;
	 */
	@Column(precision = 10, scale = 7)
	@Getter @Setter
	private BigDecimal longitude;
	
	/**
	 * 위도 y;
	 */
	@Column(precision = 10, scale = 7)
	@Getter @Setter
	private BigDecimal latitude;
}
