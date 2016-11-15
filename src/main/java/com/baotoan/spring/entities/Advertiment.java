package com.baotoan.spring.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Advertiment implements Serializable {
	private int id;
	private String name;
	private String imageUrl;
	private int status;
	private String describe;

	public Advertiment() {
	}

	public Advertiment(int id, String name, String imageUrl, int status,
			String describe) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.status = status;
		this.describe = describe;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Override
	public String toString() {
		return "Advertiment [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl
				+ ", status=" + status + ", describe=" + describe + "]";
	}

}
