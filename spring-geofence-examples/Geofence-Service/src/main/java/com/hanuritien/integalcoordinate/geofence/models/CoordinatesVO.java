package com.hanuritien.integalcoordinate.geofence.models;

import java.io.Serializable;
import java.math.BigDecimal;

import com.esri.core.geometry.MapGeometry;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CoordinatesVO implements Serializable {
	private static final long serialVersionUID = 207348541598929975L;

	/**
	 * 내부 저장 키
	 */
	@Getter @Setter
	private Integer saveKey;
	
	/**
	 * 검출 대상
	 * 저장 키
	 */
	@Getter @Setter
	private String id;
	/**
	 * 검출 판단 타입
	 * 원, 선, 도형
	 */
	@Getter @Setter
	private CoordinateType type;
	/**
	 * 실 좌표 데이터
	 * 원 : com.esri.core.geometry.Point,
	 * 선 : com.esri.core.geometry.Polyline,
	 * 도형 : com.esri.core.geometry.Polygon
	 */
	@Getter @Setter
	private MapGeometry geometry;
	/**
	 * 반경 단위 (m)
	 * 원 : 반경
	 * 선 : 두께
	 * 도형 : 무시
	 */
	@Getter @Setter
	private BigDecimal radius;
}
