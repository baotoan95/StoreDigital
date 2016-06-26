package com.baotoan.spring.dao;

import java.util.Map;

import com.baotoan.spring.entitys.Post;

public interface PostDAO {
	public boolean addPost(Post post);
	public boolean updatePost(Post post);
	public boolean deletePost(int id);
	public Post getPostById(int id);
	public Map<String, Object> getAll(int numRecordPerPage, int currentPage);
}
