package com.baotoan.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.StatisticDAO;
import com.baotoan.spring.dto.CharData;
import com.baotoan.spring.entities.OrderStatistic;

@Controller
public class AdminStatisticHandler {
	@Autowired
	private StatisticDAO statisticDAO;

	@RequestMapping(value = "/orderChartByMonth", method = RequestMethod.GET)
	public @ResponseBody CharData orderStatistic(@RequestParam("month") int month, @RequestParam("year") int year) {
		return new CharData(parseListForChart(statisticDAO.orderSttByMonth(month, year), "MONTH"));
	}
	
	@RequestMapping(value = "/orderChartByYear", method = RequestMethod.GET)
	public @ResponseBody CharData orderStatistic(@RequestParam("year") int year) {
		return new CharData(parseListForChart(statisticDAO.orderSttByYear(year), "YEAR"));
	}

	private String parseListForChart(List<OrderStatistic> sts, String type) {
		if (sts == null) {
			return "[]";
		}
		String label = type.equals("YEAR") ? "Tháng " : "Ngày ";
		StringBuilder result = new StringBuilder("[");
		for (OrderStatistic os : sts) {
			result.append("{");
			result.append("\"y\":");
			result.append(os.getY());
			result.append(",");
			result.append("\"label\":");
			result.append("\"" + label + os.getX() + "\"");
			result.append("},");
		}
		return result.substring(0, result.length() - 1) + "]";
	}
}
