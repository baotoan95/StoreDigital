package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.ProductDetailByGroup;

public class ProductDetailByGroupMapper implements RowMapper<ProductDetailByGroup> {

	public ProductDetailByGroup mapRow(ResultSet rs, int arg1) throws SQLException {
		return new ProductDetailByGroup(rs.getInt("id"), rs.getString("name"), rs.getInt("groupId"));
	}

}
