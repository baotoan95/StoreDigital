package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entitys.Status;

public class StatusMapper implements RowMapper<Status> {

	public Status mapRow(ResultSet rs, int arg1) throws SQLException {
		Status status = new Status(rs.getInt(1), rs.getString(2), rs.getString(3));
		return status;
	}

}
