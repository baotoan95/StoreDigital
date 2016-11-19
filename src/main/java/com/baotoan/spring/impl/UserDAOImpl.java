package com.baotoan.spring.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.dao.BaseDAO;
import com.baotoan.spring.dao.UserDAO;
import com.baotoan.spring.entities.User;
import com.baotoan.spring.mapper.UserMapper;
import com.baotoan.spring.utils.Pagination;

@Repository("userDAO")
public class UserDAOImpl extends BaseDAO implements UserDAO {
	public boolean addUser(User user) {
		String sql = "insert into users(mail,pass,cmnd,name,address,city,tel,score,status,role) values(?,?,?,?,?,?,?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{user.getMail(), user.getPass(), user.getId(), user.getName(),
				user.getAddress(), user.getCity(), user.getTel(), user.getScore(), user.getStatus(), user.getRole()}) > 0);
	}

	public boolean updateUser(User user) {
		String sql = "update users set pass=?,cmnd=?,name=?,address=?,city=?,tel=?,score=?,status=?,role=? where mail=?";
		return (jdbcTemplate.update(sql, new Object[]{user.getPass(), user.getId(), user.getName(), user.getAddress(),
				user.getCity(), user.getTel(), user.getScore(), user.getStatus(), user.getRole(), user.getMail()}) > 0);
	}

	public boolean deleteUser(String email) {
		String sql = "delete from users where mail=?";
		return (jdbcTemplate.update(sql, new Object[]{email}) > 0);
	}

	public User getUserByEmail(String email) {
		String sql = "select * from users where mail=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
	}

	public Map<String, Object> getAllUser(int numRecordPerPage, int currentPage) {
		String sql = "select * from users";
		
		List<User> total = jdbcTemplate.query(sql, new UserMapper());
		int totalRecordResult = total.size();
		int numPageNeedShow = 5;
		String htmlForToDoList = Pagination.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage, Pagination.TODO_LIST);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;
		List<User> listResult = jdbcTemplate.query(sql, new UserMapper());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("users", listResult);
		result.put("pagination", htmlForToDoList);
		
		return result;
	}

	public boolean activeAccount(String code) {
		try {
			String sql = "select * from users where status=?";
			User user = jdbcTemplate.queryForObject(sql, new Object[]{code}, new UserMapper());
			if(null != user) {
				user.setStatus("actived");
				updateUser(user);
				return true;
			}
		} catch (Exception e) { }
		return false;
	}

	@SuppressWarnings("deprecation")
	public int getTotalUserActive() {
		String sql = "select count(mail) from users where users.status like 'Actived%'";
		int total = 0;
		try {
			total = jdbcTemplate.queryForInt(sql);
		} catch (Exception e) {}
		return total;
	}

}
