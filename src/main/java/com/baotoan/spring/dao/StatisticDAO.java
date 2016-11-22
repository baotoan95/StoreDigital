package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entities.OrderStatistic;

public interface StatisticDAO {
	public List<OrderStatistic> orderSttByYear(int year);
	public List<OrderStatistic> orderSttByMonth(int month, int year);
}
