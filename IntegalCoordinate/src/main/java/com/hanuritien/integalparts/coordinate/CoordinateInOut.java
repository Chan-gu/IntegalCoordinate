package com.hanuritien.integalparts.coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CoordinateInOut {
	In, Out;

	private static Map<String, CoordinateInOut> namesMap = new HashMap<String, CoordinateInOut>();

	static {
		namesMap.put("in", In);
		namesMap.put("out", Out);
	}

	@JsonCreator
	public static CoordinateInOut forValue(String val) {
		return namesMap.get(StringUtils.lowerCase(val));
	}

	@JsonValue
	public String toValue() {
		for (Entry<String, CoordinateInOut> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}

		return null;
	}
}
