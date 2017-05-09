package com.hanuritien.integalcoordinate.geofence.models;

import java.io.Serializable;
import java.util.Collection;

import org.joda.time.DateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class NowPlaceVO implements Serializable{
	private static final long serialVersionUID = -108520668329266228L;

	/**
	 * 최종 수정 시간
	 */
	@Getter @Setter
	DateTime lastTime;
	
	/**
	 * 현 내역
	 */
	@Getter @Setter
	Collection<String> places;
	
	/**
	 * 경도 x;
	 */
	@Getter @Setter
	private float longitude;
	
	/**
	 * 위도 y;
	 */
	@Getter @Setter
	private float latitude;
}
