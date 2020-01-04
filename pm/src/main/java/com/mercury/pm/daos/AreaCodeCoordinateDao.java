package com.mercury.pm.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.pm.beans.AreaCodeCoordinateDTO;

public interface AreaCodeCoordinateDao extends JpaRepository<AreaCodeCoordinateDTO, String> {

}
