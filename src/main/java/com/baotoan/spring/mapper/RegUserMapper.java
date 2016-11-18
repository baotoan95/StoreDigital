package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.RegUser;

public class RegUserMapper implements RowMapper<RegUser> {

	public RegUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		RegUser reg = new RegUser(rs.getInt(1), rs.getString(2));
		return reg;
	}
	
}
