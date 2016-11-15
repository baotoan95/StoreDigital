package com.baotoan.spring.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Wish implements Serializable {
	private int id;
	private String user;
	private int productId;

	public Wish() {
	}

	public Wish(int id, String user, int productId) {
		this.id = id;
		this.user = user;
		this.productId = productId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "WishList [id=" + id + ", user=" + user + ", productId="
				+ productId + "]";
	}

}
