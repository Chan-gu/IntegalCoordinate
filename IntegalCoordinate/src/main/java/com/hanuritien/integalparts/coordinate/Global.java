/**
 * 
 */
package com.hanuritien.integalparts.coordinate;

import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

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
	@Getter @Setter
	@Value("${Coordinate.Line.Base.BufferSize}")
	private int LINEBUFFSIZE = 20;
	
	/**
	 * Redis 호스트 명칭
	 */
	@Getter @Setter
	@Value("${Coordinate.Redis.Hostname}")
	private String REDISHOSTNAME="localhost";
	
	/**
	 * Redis port
	 */
	@Getter @Setter
	@Value("${Coordinate.Redis.Port}")
	private int REDISPORT=6379;
	
	/**
	 * Redis poolsize
	 */
	@Getter @Setter
	@Value("${Coordinate.Redis.PoolSize}")
	private int PoolSize=6379;
}
