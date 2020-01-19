package com.mercury.pmdsservice.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.pmdsservice.beans.DashboardState;

public interface DashboardStateDao extends JpaRepository<DashboardState, Integer> {

	DashboardState findByUserId(int userId);
}
