package com.mercury.pm.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.pm.beans.User;
import com.mercury.pm.beans.User;

public interface UserDao extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
