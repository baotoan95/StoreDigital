package com.baotoan.spring.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.dao.BaseDAO;
import com.baotoan.spring.dao.StatisticDAO;
import com.baotoan.spring.entities.OrderStatistic;
import com.baotoan.spring.mapper.OrderStatisticMapper;

@Repository("statisticDAO")
public class StatisticDAOImpl extends BaseDAO implements StatisticDAO {

	public List<OrderStatistic> orderSttByYear(int year) {
		String sql = "select MONTH(deliverDate) as month, count(id) as count from orders where YEAR(deliverDate) = ? group by MONTH(deliverDate) order by month ASC";
		return jdbcTemplate.query(sql, new Object[] {year}, new OrderStatisticMapper());
	}

	public List<OrderStatistic> orderSttByMonth(int month, int year) {
		String sql = "select DAY(deliverDate) as day, count(id) as count from orders where MONTH(deliverDate) = ? and YEAR(deliverDate) = ? group by DAY(deliverDate) order by day ASC";
		return jdbcTemplate.query(sql, new Object[] {month, year}, new OrderStatisticMapper());
	}

}
