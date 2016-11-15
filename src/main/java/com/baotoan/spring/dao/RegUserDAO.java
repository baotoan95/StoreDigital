package com.baotoan.spring.dao;

import java.util.Map;

import com.baotoan.spring.entities.RegUser;

public interface RegUserDAO {
	public boolean addRegUserDAO(RegUser regUser);
	public boolean deleteRegUser(int id);
	public boolean updateRegUser(RegUser regUser);
	public Map<String, Object> getAll(int numRecordPerPage, int currentPage);
	public RegUser getRegUserById(int id);
	public boolean isReged(String mail);
	
}
