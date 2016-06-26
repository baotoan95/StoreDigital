package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entitys.DetailProduct;

public class DetailProductMapper implements RowMapper<DetailProduct> {

	public DetailProduct mapRow(ResultSet rs, int arg1) throws SQLException {
		DetailProduct detailProduct = new DetailProduct(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
		return detailProduct;
	}

}
