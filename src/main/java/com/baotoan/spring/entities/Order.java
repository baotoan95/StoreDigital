package com.baotoan.spring.entities;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.baotoan.spring.annotation.NotEmpty;
import com.baotoan.spring.annotation.Phone;
import com.baotoan.spring.impl.StatusDAOImpl;

@SuppressWarnings("serial")
public class Order implements Serializable {
	private String id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String address;
	@Phone
	@Size(min = 9, max = 11)
	private String tel;
	private String orderDate;
	@NotEmpty
	private String deliverDate;
	private int paymentId;
	private int totalPay;
	private String user;
	private String sellCoupon;
	private int status;
	private String statusName;

	public Order() {
	}

	public Order(String id, String name, String address, String tel,
			String orderDate, String deliverDate, int paymentId, int totalPay,
			String user, String sellCoupon, int status) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.orderDate = orderDate;
		this.deliverDate = deliverDate;
		this.paymentId = paymentId;
		this.totalPay = totalPay;
		this.user = user;
		this.sellCoupon = sellCoupon;
		this.status = status;
		this.statusName = new StatusDAOImpl().getStatusById(status).getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(String deliverDate) {
		this.deliverDate = deliverDate;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSellCoupon() {
		return sellCoupon;
	}

	public void setSellCoupon(String sellCoupon) {
		this.sellCoupon = sellCoupon;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getStatusName() {
		return statusName;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", address=" + address
				+ ", tel=" + tel + ", orderDate=" + orderDate
				+ ", deliverDate=" + deliverDate + ", paymentId=" + paymentId
				+ ", totalPay=" + totalPay + ", user=" + user + ", sellCoupon="
				+ sellCoupon + ", status=" + status + "]";
	}

}
