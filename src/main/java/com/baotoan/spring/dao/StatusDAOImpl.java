package com.baotoan.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.entities.Status;
import com.baotoan.spring.mapper.StatusMapper;

@Repository("statusDAO")
public class StatusDAOImpl extends BaseDAO implements StatusDAO {
	public Status getStatusById(int id) {
		String sql = "select * from status where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new StatusMapper());
	}

	public List<Status> getAll() {
		String sql = "select * from status";
		return jdbcTemplate.query(sql, new StatusMapper());
	}

}
