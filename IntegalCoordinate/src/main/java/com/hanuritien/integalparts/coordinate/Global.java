/**
 * 
 */
package com.hanuritien.integalparts.coordinate;

import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author changu
 * 전역 설정
 */
@Configuration
class Global {
	
	/**
	 * Rtree상 선 검출 크기(단위 : m)
	 * 선 검출 최대 크기
	 */
	@Value("${Coordinate.Line.Base.BufferSize:20}")
	public int LINEBUFFSIZE;
	
	/**
	 * Redis 호스트 명칭
	 */
	@Value("${Coordinate.Redis.URLs:'localhost:6379'}")
	public String REDISURLS;
		
	/**
	 * Redis MaxRedirects
	 */
	@Value("${Coordinate.Redis.MaxRedirects:10}")
	public int REDISMAXREDIRECTS;
	
	/**
	 * Redis PoolSize
	 */
	@Value("${Coordinate.Redis.PoolSize:20}")
	public int REDISPOOLSIZE;
	
	@Value("${Coordinate.Redis.Queue.Channel:pubsub:queuelocations}")
	public String REDISQUEUECHANNEL;
}
