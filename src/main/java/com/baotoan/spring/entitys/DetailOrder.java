package com.baotoan.spring.entitys;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DetailOrder implements Serializable {
	private int id;
	private int productId;
	private int quantity;
	private int pay;
	private String orderId;

	public DetailOrder() {
	}

	public DetailOrder(int id, int productId, int quantity, int pay, String orderId) {
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.pay = pay;
		this.orderId = orderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "DetailOrder [id=" + id + ", productId=" + productId
				+ ", quantity=" + quantity + ", pay=" + pay + ", orderId="
				+ orderId + "]";
	}

}
