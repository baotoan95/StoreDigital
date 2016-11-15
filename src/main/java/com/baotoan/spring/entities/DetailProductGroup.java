package com.baotoan.spring.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DetailProductGroup implements Serializable {
	private int id;
	private String name;

	public DetailProductGroup() {
	}

	public DetailProductGroup(int id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "DetailProductGroup [id=" + id + ", name=" + name + "]";
	}

}
