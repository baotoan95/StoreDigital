package com.baotoan.spring.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDAO {
	private static DataSource dataSource;
	public static JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		BaseDAO.dataSource = dataSource;
		BaseDAO.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
