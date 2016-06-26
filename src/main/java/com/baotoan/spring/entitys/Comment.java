package com.baotoan.spring.entitys;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Comment implements Serializable {
	private int id;
	private String nickname;
	private Date postDate;
	private String content;
	private int parentId;
	private int order;
	private int status;
	private int productId;

	public Comment() {
	}

	public Comment(int id, String nickname, Date postDate, String content,
			int parentId, int order, int status, int productId) {
		this.id = id;
		this.nickname = nickname;
		this.postDate = postDate;
		this.content = content;
		this.parentId = parentId;
		this.order = order;
		this.status = status;
		this.productId = productId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", nickname=" + nickname + ", postDate="
				+ postDate + ", content=" + content + ", parentId=" + parentId
				+ ", order=" + order + ", status=" + status + ", productId="
				+ productId + "]";
	}

}
