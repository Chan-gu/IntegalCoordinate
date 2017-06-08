package com.hanuritien.integalcoordinate.geofence.inout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanuritien.integalcoordinate.geofence.StateService;
import com.hanuritien.integalcoordinate.geofence.inout.model.Place;
import com.hanuritien.integalcoordinate.geofence.inout.model.PlaceRepository;
import com.hanuritien.integalcoordinate.geofence.inout.model.Target;
import com.hanuritien.integalcoordinate.geofence.inout.model.TargetRepository;
import com.hanuritien.integalcoordinate.geofence.models.InOutPlaceVO;
import com.hanuritien.integalcoordinate.geofence.models.NowPlaceVO;

@Primary
@Service
//@Transactional("inoutTransactionManager")
@Transactional
public class StateServiceImpl implements StateService {
	Logger logger = LoggerFactory.getLogger(StateServiceImpl.class);

	@Autowired
	PlaceRepository placeRep;	
	
	@Autowired
	TargetRepository targetRep;

	@Override
	public NowPlaceVO getLastPlace(String vId) {
		NowPlaceVO ret = new NowPlaceVO();
		ret.setLastTime(DateTime.now());
		List<String> pids = new ArrayList<String>();

		// 1 vid 체크
		for (Target tmp : targetRep.findByVId(vId)) {
			// 1-1 있을 경우
			for (Place t : tmp.getPlaces()) {
				pids.add(t.getPid());
			}
			ret.setLastTime(tmp.getLastDate());
		}
		ret.setPlaces(pids);

		return ret;
	}

	/**
	 * @param place
	 * @param lists
	 *            위치 등록
	 */
	private void insertPlace(Target target, Collection<String> lists) {
		for (String strPid : lists) {
			Place tmp = new Place();
			tmp.setTarget(target);
			tmp.setPid(strPid);
			placeRep.save(tmp);
		}

		placeRep.flush();
	}

	/**
	 * @param place
	 * @param lists
	 *            위치 삭제
	 */
	private void deletePlace(Target target, Collection<String> lists) {
		for (String strPid : lists) {
			logger.debug("deletePlace ======> " + strPid + "  target.getId() ===> " + target.getId());
			placeRep.deletebypid(strPid);
		}

		placeRep.flush();
	}

	@Override
	public InOutPlaceVO nowPlace(DateTime time, String vId, Collection<String> places, BigDecimal longitude,
			BigDecimal latitude) {
		// TODO Auto-generated method stub
		InOutPlaceVO ret = new InOutPlaceVO();
		List<String> ins = new ArrayList<String>();
		List<String> outs = new ArrayList<String>();

		logger.debug("time ======> " + time + "  vId ===> " + vId);
		
		// 1 vid 체크
		Target tmp = targetRep.findByVIDSingle(vId);
		if (tmp != null) {
			// 1-1 있을 경우

			// 1) 기존 위치 해쉬맵에 등록
			HashMap<String, Object> placeMap = new HashMap<String, Object>();
			if (tmp.getPlaces() != null) {
				for (Place tplace : tmp.getPlaces()) {
					placeMap.put(tplace.getPid(), tplace );
				}
			}

			// 2) 신규 위치를 해쉬맵에서 확인
			for (String tPid : places) {
				// 2-1) 해쉬맵에 없는 경우 도착 착지리스트에 등록
				if (!placeMap.containsKey(tPid)) {
					if (!ins.contains(tPid))
						ins.add(tPid);
				}
			}
			placeMap.clear();

			// 3) 신규 위치 해쉬맵에 등록
			HashMap<String, Object> newMap = new HashMap<String, Object>();
			for (String tPid : places) {
				newMap.put(tPid, tPid);
			}

			// 4) 기존 위치를 신규위치 해쉬맵에서 확인
			if (tmp.getPlaces() != null) {
				for (Place tplace : tmp.getPlaces()) {
					if (!newMap.containsKey(tplace.getPid())) {
						outs.add(tplace.getPid());
					}
				}
			}
			// 5) 최종시간 수정
			tmp.setLastDate(time);
			tmp.setLatitude(latitude);
			tmp.setLongitude(longitude);
			
			targetRep.save(tmp);
			targetRep.flush();
			// 도착리스트 차량위치 등록
			insertPlace(tmp, ins);
			// 출발리스트 차량위치 삭제
			deletePlace(tmp, outs);

		} else {
			// 1-2 없을 경우
			// 1) vid 등록
			tmp = new Target();
			tmp.setVid(vId);
			tmp.setLastDate(time);
			tmp.setLatitude(latitude);
			tmp.setLongitude(longitude);

			// 2) 도착 리스트에 신규 위치들 등록
			for (String tPid : places) {
				if (!ins.contains(tPid))
					ins.add(tPid);
			}
			targetRep.save(tmp);
			targetRep.flush();
			// 도착리스트 차량위치 등록
			insertPlace(tmp, ins);
		}

		ret.setLastTime(time);
		ret.setPlaceIns(ins);
		ret.setPlaceOuts(outs);
		return ret;
	}

}
