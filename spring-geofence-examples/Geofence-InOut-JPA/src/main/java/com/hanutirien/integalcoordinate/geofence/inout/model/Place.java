package com.hanutirien.integalcoordinate.geofence.inout.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "target") 
@Entity(name = "tbl_places")
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"tbl_targets_id", "pid"})
})
public class Place extends AbstractPersistable<Integer>{
	private static final long serialVersionUID = 6696481813365448699L;

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="tbl_targets_id")
	@Setter @Getter
	private Target target;
	
	@Column(name="pid", nullable=false)
	@Setter @Getter
	private String pid;
}
