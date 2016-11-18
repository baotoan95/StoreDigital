package com.baotoan.spring.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Status implements Serializable {
	private int id;
	private String name;
	private String descript;

	public Status() {
	}

	public Status(int id, String name, String descript) {
		this.id = id;
		this.name = name;
		this.descript = descript;
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", name=" + name + ", descript=" + descript
				+ "]";
	}

}
