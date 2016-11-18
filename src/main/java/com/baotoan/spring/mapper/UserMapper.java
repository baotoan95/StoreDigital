package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.User;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int row) throws SQLException {
		User user = new User(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10));
		return user;
	}

}
