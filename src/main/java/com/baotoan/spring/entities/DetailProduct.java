package com.baotoan.spring.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DetailProduct implements Serializable {
	private int id;
	private String name;
	private int productId;
	private String value;
	private int group;

	public DetailProduct() {
	}

	public DetailProduct(int id, String name, int productId, String value,
			int group) {
		this.id = id;
		this.name = name;
		this.productId = productId;
		this.value = value;
		this.group = group;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "DetailProduct [id=" + id + ", name=" + name + ", productId="
				+ productId + ", value=" + value + ", group=" + group + "]";
	}

}
