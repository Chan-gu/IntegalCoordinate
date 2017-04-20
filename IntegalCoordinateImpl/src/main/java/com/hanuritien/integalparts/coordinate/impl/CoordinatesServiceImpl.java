package com.hanuritien.integalparts.coordinate.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import com.hanuritien.integalparts.coordinate.CoordinatesService;
import com.hanuritien.integalparts.coordinate.model.CoordinatesVO;

public class CoordinatesServiceImpl implements CoordinatesService {
	
	@Resource(name="CoordinateDAO")
	CoordinateDAO coorddao;
	
	@Override
	public void save(List<CoordinatesVO> inputs) throws Exception {
		for (CoordinatesVO tmp : inputs) {
			if (tmp.getId() == null || tmp.getId().isEmpty()) {
				tmp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			}
//			if (tmp.getAddress() == null) {
//				Address t = addsvc.findByCode(tmp.getCode());
//				if (t != null) {
//					tmp.setAddress(t.getAddress());
//				}
//			}
			
			coorddao.save(tmp);
//			addchk.addCoordinatesVO(tmp);
		}
	}

	@Override
	public void delete(List<CoordinatesVO> inputs) throws Exception {
		for (CoordinatesVO tmp : inputs) {
			coorddao.delete(tmp.getId());
		}
	}

	@Override
	public List<CoordinatesVO> findAll() throws Exception {
		return coorddao.findAll();
	}

	@Override
	public CoordinatesVO find(String id) throws Exception {
		// TODO Auto-generated method stub
		return coorddao.find(id);
	}

}
