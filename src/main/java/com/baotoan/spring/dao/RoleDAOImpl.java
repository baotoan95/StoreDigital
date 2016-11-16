package com.baotoan.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.entities.Role;
import com.baotoan.spring.mapper.RoleMapper;

@Repository("roleDAO")
public class RoleDAOImpl extends BaseDAO implements RoleDAO {

	public Role getRoleById(int id) {
		String sql = "select * from roles where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RoleMapper());
	}

	public List<Role> getAll() {
		String sql = "select * from roles";
		return jdbcTemplate.query(sql, new RoleMapper());
	}

}
