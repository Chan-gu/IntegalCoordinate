/**
 * 
 */
package com.hanuritien.integalparts.coordinate.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.hanuritien.integalparts.coordinate.CoordinateInOut;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author changu
 * 실시간 위치정보 객체
 */
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RLocationVO implements Serializable {
	private static final long serialVersionUID = 1030134298843973234L;

	/**
	 *  대상 아이디 
	 *  차량 구분정보(차량번호 혹은 모뎀번호)
	 *  대상 정보를 기준으로 검출정보를 통지한다.
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
	 * 데이터 발생시간
	 */
	@Getter @Setter
	private DateTime timeSighting;
	
	/**
	 * 인아웃 여부
	 */
	@Getter @Setter
	private CoordinateInOut inout;
	
	/**
	 * 일치 대상
	 */
	@Getter @Setter
	private String match;
}
