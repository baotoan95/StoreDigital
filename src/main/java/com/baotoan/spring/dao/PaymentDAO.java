package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entitys.Payment;

public interface PaymentDAO {
	public Payment getPaymentById(int id);
	public List<Payment> getPayments();
}
