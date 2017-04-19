package com.hanuritien.integalparts.coordinate.model;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author changu
 * 현위치 정보 응답
 */
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PUBLIC)
public class ResultPlaceVO implements Serializable {
	private static final long serialVersionUID = 5033526693288565452L;

	/**
	 * 대상 아이디
	 */
	@Getter @Setter
	private String vID;
	
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
	
	/**
	 * 검출 반환 아이디 리스트
	 */
	@Getter @Setter
	private List<String> matches;
	
	/**
	 * 데이터 발생시간
	 */
	@Getter @Setter
	private DateTime timeSighting;
}
