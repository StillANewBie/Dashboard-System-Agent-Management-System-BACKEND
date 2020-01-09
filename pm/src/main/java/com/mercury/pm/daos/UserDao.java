package com.mercury.pm.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.pm.beans.Login;

public interface UserDao extends JpaRepository<Login, Integer> {
	Login findByUsername(String username);
}
