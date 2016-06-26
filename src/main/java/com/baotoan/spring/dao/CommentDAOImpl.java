package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entitys.Comment;
import com.baotoan.spring.mapper.CommentMapper;

public class CommentDAOImpl extends BaseDAO implements CommentDAO {

	public boolean addComment(Comment comment) {
		String sql = "insert into comments(nickname,postDate,content,parentId,order,status,prodId) values(?,?,?,?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{comment.getNickname(), comment.getPostDate(),
		comment.getContent(), comment.getParentId(), comment.getOrder(), comment.getStatus(), comment.getProductId()}) > 0);
	}

	public boolean updateComment(Comment comment) {
		String sql = "update comments set nickname=?,postDate=?,content=?,parentId=?,order=?,status=?,prodId=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{comment.getNickname(), comment.getPostDate(),
		comment.getContent(), comment.getParentId(), comment.getOrder(), comment.getStatus(), comment.getProductId(),
		comment.getId()}) > 0);
	}
	
	public boolean deleteComment(int id) {
		String sql = "delete * from commnets where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public Comment getCommentById(int id) {
		String sql = "select * from comments where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CommentMapper());
	}

	public List<Comment> getCommentsByParentId(int parentId) {
		String sql = "select * from comments where parentId=?";
		return jdbcTemplate.query(sql, new Object[]{parentId}, new CommentMapper());
	}

	public List<Comment> getcomCommentsByProductId(int productId) {
		String sql = "select * from comments where prodId=?";
		return jdbcTemplate.query(sql, new CommentMapper());
	}

	@SuppressWarnings("deprecation")
	public int getTotalNewComment() {
		String sql = "select count(id) from comments where status=0";
		int total = 0;
		try {
			total = jdbcTemplate.queryForInt(sql);
		} catch (Exception e) { }
		return total;
	}

}
