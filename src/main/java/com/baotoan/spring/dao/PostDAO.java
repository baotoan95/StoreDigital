package com.baotoan.spring.dao;

import java.util.List;
import java.util.Map;

import com.baotoan.spring.entities.Post;

public interface PostDAO {
	public boolean addPost(Post post);
	public boolean updatePost(Post post);
	public boolean deletePost(int id);
	public Post getPostById(int id);
	public List<Post> getAllWithoutContent();
	public Map<String, Object> getAll(int numRecordPerPage, int currentPage);
}
