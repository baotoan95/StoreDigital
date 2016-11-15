package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entities.Role;

public interface RoleDAO {
	public Role getRoleById(int id);
	public List<Role> getAll();
}
