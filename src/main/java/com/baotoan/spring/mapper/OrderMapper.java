package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.Order;

public class OrderMapper implements RowMapper<Order> {

	public Order mapRow(ResultSet rs, int arg1) throws SQLException {
		Order order = new Order(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
				rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11));
		return order;
	}
	
}
