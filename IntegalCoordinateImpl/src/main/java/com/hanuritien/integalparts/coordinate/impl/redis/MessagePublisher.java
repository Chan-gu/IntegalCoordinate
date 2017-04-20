package com.hanuritien.integalparts.coordinate.impl.redis;

import com.hanuritien.integalparts.coordinate.model.ResultPlaceVO;

public interface MessagePublisher {
	 /**
	 * @param message
	 * 메세지 Pub
	 */
	void publish(final String message);
	void publishVO(final ResultPlaceVO message);
}
