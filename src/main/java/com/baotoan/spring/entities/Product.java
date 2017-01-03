package com.baotoan.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Product implements Serializable {
	private int id;
	private String name;
	private int oldPrice;
	private int newPrice;
	private int views;
	private int reviews;
	private String tags;
	private int postId;
	private int promotionId;
	private Date importDate;
	private int cateId;
	private String description;
	private int quantity;

	// Addition
	private String urlImage;
	private List<Image> listImage;
	private Map<String, Map<String, DetailProduct>> detail;

	public Product() {
	}

	public Product(int id, String name, int oldPrice, int newPrice, int views, int reviews, String tags, int postId,
			int promotionId, Date importDate, int cateId, String description) {
		this.id = id;
		this.name = name;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.views = views;
		this.reviews = reviews;
		this.tags = tags;
		this.postId = postId;
		this.promotionId = promotionId;
		this.importDate = importDate;
		this.cateId = cateId;
		this.description = description;
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

	public int getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(int oldPrice) {
		this.oldPrice = oldPrice;
	}

	public int getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(int newPrice) {
		this.newPrice = newPrice;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getReviews() {
		return reviews;
	}

	public void setReviews(int reviews) {
		this.reviews = reviews;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<Image> getListImage() {
		return listImage;
	}

	public void setListImage(List<Image> listImage) {
		this.listImage = listImage;
	}

	public Map<String, Map<String, DetailProduct>> getDetail() {
		return detail;
	}

	public void setDetail(Map<String, Map<String, DetailProduct>> detail) {
		this.detail = detail;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", oldPrice=" + oldPrice + ", newPrice=" + newPrice + ", views="
				+ views + ", reviews=" + reviews + ", tags=" + tags + ", postId=" + postId + ", promotionId="
				+ promotionId + ", importDate=" + importDate + ", cateId=" + cateId + ", describe=" + description
				+ ", urlImage=" + urlImage + "]";
	}

}
