package com.hanuritien.integalparts.coordinate.utils.InOutCheckerJPA.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

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
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="target", fetch=FetchType.LAZY)
	@Setter @Getter	
	private Collection<Place> places;
}