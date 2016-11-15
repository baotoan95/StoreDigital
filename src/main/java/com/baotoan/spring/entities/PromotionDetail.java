package com.baotoan.spring.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PromotionDetail implements Serializable {
	private int id;
	private String detail;
	private String describe;
	private int promotionId;

	public PromotionDetail() {
	}

	public PromotionDetail(int id, String detail, String describe,
			int promotionId) {
		this.id = id;
		this.detail = detail;
		this.describe = describe;
		this.promotionId = promotionId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	@Override
	public String toString() {
		return "PromotionDetail [id=" + id + ", detail=" + detail
				+ ", describe=" + describe + ", promotionId=" + promotionId
				+ "]";
	}

}
