package com.hanuritien.integalparts.coordinate.utils.GeofenceAddQueue;

import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;

public interface MessagePublisher {
	void publishVO(final CoordinatesVO message);
}
