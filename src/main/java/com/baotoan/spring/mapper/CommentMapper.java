package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entitys.Comment;

public class CommentMapper implements RowMapper<Comment> {

	public Comment mapRow(ResultSet rs, int arg1) throws SQLException {
		Comment comment = new Comment(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4), 
				rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
		return comment;
	}

}
