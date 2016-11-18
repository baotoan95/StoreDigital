package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entities.Comment;

public interface CommentDAO {
	public boolean addComment(Comment comment);
	public boolean updateComment(Comment comment);
	public boolean deleteComment(int id);
	public Comment getCommentById(int id);
	public List<Comment> getCommentsByParentId(int parentId);
	public List<Comment> getcomCommentsByProductId(int productId);
	public int getTotalNewComment();
}
