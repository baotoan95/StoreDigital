package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entitys.Image;

public class ImageMapper implements RowMapper<Image> {

	public Image mapRow(ResultSet rs, int arg1) throws SQLException {
		Image image = new Image(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5));
		return image;
	}

}
