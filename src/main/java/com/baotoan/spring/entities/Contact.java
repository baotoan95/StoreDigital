package com.baotoan.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.baotoan.spring.annotation.NotEmpty;
import com.baotoan.spring.annotation.Phone;

@SuppressWarnings("serial")
public class Contact implements Serializable {
	private int id;
	@NotEmpty
	private String name;
	private String company;
	@NotEmpty
	private String address;
	@Phone
	private String tel;
	@Email @NotEmpty
	private String mail;
	@NotEmpty @Size(min = 10, max = 255)
	private String content;
	private Date date;
	private int status;

	public Contact() {
	}

	public Contact(int id, String name, String company, String address,
			String tel, String mail, String content, Date date, int status) {
		this.id = id;
		this.name = name;
		this.company = company;
		this.address = address;
		this.tel = tel;
		this.mail = mail;
		this.content = content;
		this.date = date;
		this.status = status;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", company=" + company
				+ ", address=" + address + ", tel=" + tel + ", mail=" + mail
				+ ", content=" + content + ", date=" + date + ", status="
				+ status + "]";
	}

}
