package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.Advertiment;

public class AdvertimentMapper implements RowMapper<Advertiment> {

	public Advertiment mapRow(ResultSet rs, int arg1) throws SQLException {
		Advertiment advertiment = new Advertiment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
		return advertiment;
	}

}
