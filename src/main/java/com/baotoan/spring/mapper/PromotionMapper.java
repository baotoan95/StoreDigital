package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entitys.Promotion;

public class PromotionMapper implements RowMapper<Promotion> {

	public Promotion mapRow(ResultSet rs, int arg1) throws SQLException {
		Promotion promotion = new Promotion(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5));
		return promotion;
	}

}
