package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entitys.PromotionDetail;

public class PromotionDetailMapper implements RowMapper<PromotionDetail> {

	public PromotionDetail mapRow(ResultSet rs, int arg1) throws SQLException {
		PromotionDetail promotionDetail = new PromotionDetail(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		return promotionDetail;
	}

}
