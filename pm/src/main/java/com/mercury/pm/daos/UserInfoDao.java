package com.mercury.pm.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.pm.beans.UserInfo;


public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {

}
