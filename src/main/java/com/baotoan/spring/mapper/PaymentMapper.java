package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.Payment;

public class PaymentMapper implements RowMapper<Payment> {

	public Payment mapRow(ResultSet rs, int arg1) throws SQLException {
		Payment payment = new Payment(rs.getInt(1), rs.getString(2), rs.getString(3));
		return payment;
	}

}
