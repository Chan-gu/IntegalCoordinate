package com.hanuritien.integalparts.coordinate.redis;

public interface MessagePublisher {
	 /**
	 * @param message
	 * 메세지 Pub
	 */
	void publish(final String message);
}
