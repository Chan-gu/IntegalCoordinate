package com.hanuritien.integalparts.coordinate.back;

import java.util.List;

import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;

public interface CoordinatesService {
	void save(List<CoordinatesVO> inputs) throws Exception;
	void delete(List<CoordinatesVO> inputs) throws Exception;	
	List<CoordinatesVO> findAll() throws Exception;	
	CoordinatesVO find(String id) throws Exception;
}
