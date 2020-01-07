package com.mercury.pm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.pm.beans.AreaCodeCoordinate;
import com.mercury.pm.daos.AreaCodeCoordinateDao;

@Service
public class AreaCodeCoordinateService {
	
	@Autowired
	private AreaCodeCoordinateDao accd;
	
	public List<AreaCodeCoordinate> getAreaCodeCoordinates() {
		return accd.findAll();
	}
}
