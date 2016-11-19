package com.baotoan.spring.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.dao.BaseDAO;
import com.baotoan.spring.dao.RegUserDAO;
import com.baotoan.spring.entities.RegUser;
import com.baotoan.spring.mapper.RegUserMapper;
import com.baotoan.spring.utils.Pagination;

@Repository("regUserDAO")
public class RegUserDAOImpl extends BaseDAO implements RegUserDAO {
	public boolean addRegUserDAO(RegUser regUser) {
		String sql = "insert into reg_user(mail) values(?)";
		return (jdbcTemplate.update(sql, new Object[]{regUser.getMail()}) > 0);
	}

	public boolean deleteRegUser(int id) {
		String sql = "delete * from reg_user where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public boolean updateRegUser(RegUser regUser) {
		String sql = "update reg_user set mail=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{regUser.getMail(), regUser.getId()}) > 0);
	}

	public Map<String, Object> getAll(int numRecordPerPage, int currentPage) {
		String sql = "select * from reg_user";
		
		List<RegUser> total = jdbcTemplate.query(sql, new RegUserMapper());
		int totalRecordResult = total.size();
		int numPageNeedShow = 5;
		String htmlForToDoList = Pagination.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage, Pagination.TODO_LIST);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;

		List<RegUser> listResult = jdbcTemplate.query(sql, new RegUserMapper());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("regUsers", listResult);
		result.put("pagination", htmlForToDoList);
		
		return result;
	}

	public RegUser getRegUserById(int id) {
		String sql = "select * from reg_user where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RegUserMapper());
	}
	
	public boolean isReged(String mail) {
		String sql = "select * from reg_user where mail=?";
		RegUser reg = null;
		try {
			reg = jdbcTemplate.queryForObject(sql,new Object[]{mail} , new RegUserMapper());
		} catch (Exception e) {
			return false;
		}
		return (reg != null);
	}

}
