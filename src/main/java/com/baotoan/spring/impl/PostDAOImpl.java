package com.baotoan.spring.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.dao.BaseDAO;
import com.baotoan.spring.dao.PostDAO;
import com.baotoan.spring.entities.Post;
import com.baotoan.spring.mapper.PostMapper;
import com.baotoan.spring.utils.Pagination;

@Repository("postDAO")
public class PostDAOImpl extends BaseDAO implements PostDAO {

	public boolean addPost(Post post) {
		String sql = "insert into posts(title,content,publishDate,author) values(?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{post.getTitle(), post.getContent(), post.getPublishDate(), post.getAuthor()}) > 0);
	}

	public boolean updatePost(Post post) {
		String sql = "update posts set title=?,content=?,publishDate=?,author=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{post.getTitle(), post.getContent(), post.getPublishDate(), post.getAuthor(), post.getId()}) > 0);
	}

	public boolean deletePost(int id) {
		String sql = "delete from posts where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public Post getPostById(int id) {
		String sql = "select * from posts where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PostMapper());
	}
	
	public List<Post> getAllWithoutContent() {
		String sql = "select * from posts";
		return (List<Post>) jdbcTemplate.query(sql, new PostMapper());
	}

	public Map<String, Object> getAll(int numRecordPerPage, int currentPage) {
		String sql = "select * from posts";
		
		List<Post> total = jdbcTemplate.query(sql, new PostMapper());
		int totalRecordResult = total.size();
		int numPageNeedShow = 5;
		String htmlForToDoList = Pagination.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage, Pagination.TODO_LIST);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;
		List<Post> listResult = jdbcTemplate.query(sql, new PostMapper());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("posts", listResult);
		result.put("pagination", htmlForToDoList);
		
		return result;
	}

}
