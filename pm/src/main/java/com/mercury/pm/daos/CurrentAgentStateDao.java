package com.mercury.pm.daos;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mercury.pm.beans.CurrentAgentStateDTO;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.data.jpa.repository.query.Procedure;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//import com.mercury.pm.beans.CurrentAgentStateDTO;
//
//public interface CurrentAgentStateDao extends CrudRepository<CurrentAgentStateDTO, Integer> {
//	@Transactional
//	@Procedure(procedureName = "get_currentagentstates_by_groupid")
//	List<CurrentAgentStateDTO> getCurrentAgentStatesByGroupId(@Param("gid") int groupId);
//}

@Repository("currentAgentStateRepository")
public interface CurrentAgentStateDao extends CrudRepository<CurrentAgentStateDTO, Integer> {

//	@Query(value = "{call get_currentagentstates_by_groupid}", nativeQuery = true)
//	public List<CurrentAgentStateDTO> findAll(@Param("gid") int groupId);
	@Procedure(name = "get_currentagentstates_by_groupid")
	List<CurrentAgentStateDTO> getCurrentAgentStatesByGroupId(int gid);
}