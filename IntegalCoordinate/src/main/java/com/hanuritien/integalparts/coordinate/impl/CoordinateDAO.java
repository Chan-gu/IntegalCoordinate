package com.hanuritien.integalparts.coordinate.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;

@Repository("CoordinateDAO")
public class CoordinateDAO {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	private final String COORDINATE_KEY = "CoordinateCheck";
	
	void save(CoordinatesVO input) {
		this.redisTemplate.opsForHash().put(COORDINATE_KEY, input.getId(), input);
	}
	
	CoordinatesVO find(String id) {
		return (CoordinatesVO)this.redisTemplate.opsForHash().get(COORDINATE_KEY, id);
	}
	
	List<CoordinatesVO> findAll() {
		return new ArrayList(this.redisTemplate.opsForHash().entries(COORDINATE_KEY).values());
	}
	
	void delete(String id) {
		this.redisTemplate.opsForHash().delete(COORDINATE_KEY, id);
	}
}
