package com.hanuritien.integalparts.coordinate.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author changu
 * 검출 방법
 * circle 포인트 (반지름이 있는)/
 * line 선 (두께가 있는)/
 * polygon 도형
 */
public enum CoordinateType {
	Circle,
	Line,
	Polygon;
	
	private static Map<String, CoordinateType> namesMap = new HashMap<String, CoordinateType>();
	
	static {
		namesMap.put("circle", Circle);
		namesMap.put("line", Line);
		namesMap.put("polygon", Polygon);
	}	
	
	@JsonCreator
	public static CoordinateType forValue(String val){
		return namesMap.get(StringUtils.lowerCase(val));
	}

	@JsonValue
	public String toValue() {
		for (Entry<String, CoordinateType> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}
		
		return null;
	}	
}
