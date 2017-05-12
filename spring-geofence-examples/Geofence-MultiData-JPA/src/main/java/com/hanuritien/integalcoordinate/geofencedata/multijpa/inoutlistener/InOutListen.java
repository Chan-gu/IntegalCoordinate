package com.hanuritien.integalcoordinate.geofencedata.multijpa.inoutlistener;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity(name = "tbl_inout")
public class InOutListen extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = -4042423042923110020L;

	@Setter @Getter
	private String targetID;
	
	@Setter @Getter
	private String vehiceID;
	
	@Setter @Getter
	private boolean inCheck;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Setter @Getter
	private DateTime timeGPS;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Setter @Getter
	private DateTime createDate = DateTime.now(DateTimeZone.forID("Asia/Seoul"));
}
