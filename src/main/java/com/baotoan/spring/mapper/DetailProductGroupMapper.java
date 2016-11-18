package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.DetailProductGroup;

public class DetailProductGroupMapper implements RowMapper<DetailProductGroup> {

	public DetailProductGroup mapRow(ResultSet rs, int arg1) throws SQLException {
		DetailProductGroup detailProductGroup = new DetailProductGroup(rs.getInt(1), rs.getString(2));
		return detailProductGroup;
	}

}
