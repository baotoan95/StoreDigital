package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entitys.Payment;
import com.baotoan.spring.mapper.PaymentMapper;

public class PaymentDAOImpl extends BaseDAO implements PaymentDAO {

	public Payment getPaymentById(int id) {
		String sql = "select * from payments where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PaymentMapper());
	}

	public List<Payment> getPayments() {
		String sql = "select * from payments";
		return jdbcTemplate.query(sql, new PaymentMapper());
	}

}
