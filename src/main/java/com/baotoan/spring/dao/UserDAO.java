package com.baotoan.spring.dao;

import java.util.Map;

import com.baotoan.spring.entitys.User;

public interface UserDAO {
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String email);
	public User getUserByEmail(String email);
	public Map<String, Object> getAllUser(int numRecordPerPage, int currentPage);
	public boolean activeAccount(String code);
	public int getTotalUserActive();
}
