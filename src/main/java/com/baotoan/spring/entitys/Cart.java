package com.baotoan.spring.entitys;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cart implements Serializable {
	private int id;
	private String name;
	private int price;
	private int quantity;
	private int totalPay;
	private String imageUrl;

	public Cart() {
	}

	public Cart(int id, String name, int price, int quantity, int totalPay,
			String imageUrl) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.totalPay = totalPay;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + ", totalPay=" + totalPay
				+ ", imageUrl=" + imageUrl + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cart) {
			return this.id == ((Cart) obj).id;
		}
		return false;
	}

}
