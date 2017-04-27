package com.hanuritien.integalparts.coordinate.model;

import java.io.Serializable;
import java.util.Collection;

import org.joda.time.DateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author changu
 * 출발 위치 / 도착 위치
 */
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class InOutPlaceVO implements Serializable {
	private static final long serialVersionUID = 7352155155575961822L;
	
	/**
	 * 최종 수정 시간
	 */
	@Getter @Setter
	DateTime lastTime;
	 
	/**
	 * 도착 내역
	 */
	@Getter @Setter
	Collection<String> placeIns;
	
	/**
	 * 출발내역
	 */
	@Getter @Setter
	Collection<String> placeOuts;
}
