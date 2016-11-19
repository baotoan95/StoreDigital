package com.baotoan.spring.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.dao.BaseDAO;
import com.baotoan.spring.dao.PaymentDAO;
import com.baotoan.spring.entities.Payment;
import com.baotoan.spring.mapper.PaymentMapper;

@Repository("paymentDAO")
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
