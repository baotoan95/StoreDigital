package com.baotoan.spring.entities;

import java.io.Serializable;
import java.util.Date;

import com.baotoan.spring.annotation.NotEmpty;

@SuppressWarnings("serial")
public class Post implements Serializable {
	private int id;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	private Date publishDate;
	@NotEmpty
	private String author;

	public Post() {
	}

	public Post(int id, String title, String content, Date publishDate,
			String author) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.publishDate = publishDate;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content
				+ ", publishDate=" + publishDate + ", author=" + author + "]";
	}

}
