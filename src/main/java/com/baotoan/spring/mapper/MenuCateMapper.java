package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.MenuCate;

public class MenuCateMapper implements RowMapper<MenuCate> {

	public MenuCate mapRow(ResultSet rs, int arg1) throws SQLException {
		MenuCate menuCate = new MenuCate(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
		return menuCate;
	}

}
