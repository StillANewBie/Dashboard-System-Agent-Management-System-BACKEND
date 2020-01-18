package com.mercury.pm.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.pm.beans.DashboardState;

public interface DashboardStateDao extends JpaRepository<DashboardState, Integer> {

	DashboardState findByUserId(int userId);
}
