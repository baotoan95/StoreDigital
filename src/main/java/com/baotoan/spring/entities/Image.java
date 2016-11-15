package com.baotoan.spring.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Image implements Serializable {
	private int id;
	private String name;
	private String url;
	private int productId;
	private boolean isAvatar;

	public Image() {
	}

	public Image(int id, String name, String url, int productId,
			boolean isAvatar) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.productId = productId;
		this.isAvatar = isAvatar;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean isAvatar() {
		return isAvatar;
	}

	public void setAvatar(boolean isAvatar) {
		this.isAvatar = isAvatar;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", url=" + url
				+ ", productId=" + productId + ", isAvatar=" + isAvatar + "]";
	}

}
