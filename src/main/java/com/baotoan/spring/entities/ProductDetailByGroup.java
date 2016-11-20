package com.baotoan.spring.entities;

public class ProductDetailByGroup {
	private int id;
	private String name;
	private int groupId;

	public ProductDetailByGroup(int id, String name, int groupId) {
		super();
		this.id = id;
		this.name = name;
		this.groupId = groupId;
	}

	public ProductDetailByGroup() {
		// TODO Auto-generated constructor stub
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

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "ProductDetailByGroup [id=" + id + ", name=" + name + ", groupId=" + groupId + "]";
	}

}
