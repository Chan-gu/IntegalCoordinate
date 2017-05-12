package com.hanuritien.integalcoordinate.geofencedata.multijpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanuritien.integalcoordinate.geofence.InOutListener;
import com.hanuritien.integalcoordinate.geofence.models.CoordinateInOut;
import com.hanuritien.integalcoordinate.geofencedata.multijpa.inoutlistener.InOutListen;
import com.hanuritien.integalcoordinate.geofencedata.multijpa.inoutlistener.InOutListenerRepository;

@Primary
@Service
@Transactional("coordinateTransactionManager")
public class InOutListenerImpl implements InOutListener {
	Logger logger = LoggerFactory.getLogger(InOutListenerImpl.class);

	@Autowired
	InOutListenerRepository inoutlisten;

	@Override
	public void actionInOut(CoordinateInOut inout, Collection<String> ids, String vId, DateTime datetime) {
		// TODO Auto-generated method stub

		List<InOutListen> list = new ArrayList<InOutListen>();
		for (String id : ids) {
			InOutListen tmp = new InOutListen();
			tmp.setInCheck(inout == CoordinateInOut.In);
			tmp.setTargetID(id);
			tmp.setTimeGPS(datetime);
			tmp.setVehiceID(vId);
			list.add(tmp);
		}
		inoutlisten.save(list);
		inoutlisten.flush();
	}

}
