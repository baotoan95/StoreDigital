package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entitys.Post;

public class PostMapper implements RowMapper<Post> {

	public Post mapRow(ResultSet rs, int arg1) throws SQLException {
		Post post = new Post(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5));
		return post;
	}

}
