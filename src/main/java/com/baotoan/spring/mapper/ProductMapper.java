package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.Product;

public class ProductMapper implements RowMapper<Product> {

	public Product mapRow(ResultSet rs, int arg1) throws SQLException {
		Product product = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), 
				rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getDate(10), rs.getInt(11), rs.getString(12));
		return product;
	}

}
