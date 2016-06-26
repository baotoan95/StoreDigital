package com.baotoan.spring.entitys;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Promotion implements Serializable {
	private int id;
	private Date start;
	private Date end;
	private String name;
	private String imageUrl;

	public Promotion() {
	}

	public Promotion(int id, Date start, Date end, String name, String imageUrl) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.name = name;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
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


	@Override
	public String toString() {
		return "Promotion [id=" + id + ", start=" + start + ", end=" + end
				+ ", name=" + name + ", imageUrl=" + imageUrl + "]";
	}

}
