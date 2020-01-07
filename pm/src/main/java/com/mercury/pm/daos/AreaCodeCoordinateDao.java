package com.mercury.pm.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.pm.beans.AreaCodeCoordinate;

public interface AreaCodeCoordinateDao extends JpaRepository<AreaCodeCoordinate, String> {

}
