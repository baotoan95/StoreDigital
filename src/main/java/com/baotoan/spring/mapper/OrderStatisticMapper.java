package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.OrderStatistic;

public class OrderStatisticMapper implements RowMapper<OrderStatistic> {

	public OrderStatistic mapRow(ResultSet rs, int arg1) throws SQLException {
		return new OrderStatistic(rs.getString(1), rs.getString(2));
	}

}
