package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entitys.Role;

public class RoleMapper implements RowMapper<Role> {

	public Role mapRow(ResultSet rs, int arg1) throws SQLException {
		Role role = new Role(rs.getInt(1), rs.getString(2));
		return role;
	}

}
