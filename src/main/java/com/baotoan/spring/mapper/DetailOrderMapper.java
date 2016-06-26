package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entitys.DetailOrder;

public class DetailOrderMapper implements RowMapper<DetailOrder> {

	public DetailOrder mapRow(ResultSet rs, int arg1) throws SQLException {
		DetailOrder detailOrder = new DetailOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
		return detailOrder;
	}

}
