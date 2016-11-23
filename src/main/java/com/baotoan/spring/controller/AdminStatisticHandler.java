package com.baotoan.spring.controller;

import java.util.ArrayList;
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
		System.out.println(creatNewArr(statisticDAO.orderSttByMonth(month, year), 30));
		return new CharData(parseListForChart(creatNewArr(statisticDAO.orderSttByMonth(month, year), 30), "MONTH"));
	}
	
	@RequestMapping(value = "/orderChartByYear", method = RequestMethod.GET)
	public @ResponseBody CharData orderStatistic(@RequestParam("year") int year) {
		return new CharData(parseListForChart(creatNewArr(statisticDAO.orderSttByYear(year), 12), "YEAR"));
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
	
	private static List<OrderStatistic> creatNewArr(List<OrderStatistic> arrlst, int max) {
		List<OrderStatistic> ch = new ArrayList<OrderStatistic>();
		for (int i = 0; i < arrlst.size(); i++) {
			int value = Integer.parseInt((String)arrlst.get(i).getX());
			if (i == 0) {
				for (int k = 0; k < value; k++) {
					if(k == (value - 1)) {
						ch.add(new OrderStatistic(k + 1, arrlst.get(i).getY()));
					} else {
						ch.add(new OrderStatistic(k + 1, 0));
					}
				}
			}
			if (i > 0) {
				for (int l = Integer.parseInt((String) arrlst.get(i - 1).getX()); l < value; l++) {
					if(l == (value - 1)) {
						ch.add(new OrderStatistic(l + 1, arrlst.get(i).getY()));
					} else {
						ch.add(new OrderStatistic(l + 1, 0));
					}
				}
			}
		}
		for (int m = Integer.parseInt((String) arrlst.get(arrlst.size() - 1).getX()); m < max; m++) {
			ch.add(new OrderStatistic(m + 1, 0));
		}

		return ch;
	}
	
	private List<OrderStatistic> createNew(List<OrderStatistic> old, int max) {
		List<OrderStatistic> rs = new ArrayList<OrderStatistic>();
		int count = 0;
		for(int i = 0; i < max; i++) {
			if(Integer.parseInt((String)old.get(count).getX()) == i && count < old.size()) {
				rs.add(old.get(i));
				count++;
			} else {
				rs.add(new OrderStatistic(i, 0));
			}
		}
		return rs;
	}
}
