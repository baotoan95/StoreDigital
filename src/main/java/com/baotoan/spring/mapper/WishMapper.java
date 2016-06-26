package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entitys.Wish;

public class WishMapper implements RowMapper<Wish> {

	public Wish mapRow(ResultSet rs, int row) throws SQLException {
		Wish wish = new Wish(rs.getInt(1), rs.getString(2), rs.getInt(3));
		return wish;
	}

}
