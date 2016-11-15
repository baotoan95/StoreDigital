package com.baotoan.spring.entities;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.baotoan.spring.annotation.NotEmpty;
import com.baotoan.spring.annotation.Phone;

public class User {
	@NotEmpty @Email
	private String mail;
	@Size(min=8, max=20)
	private String pass;
	private int id;
	@NotEmpty
	private String name;
	private String address;
	private String city;
	@Phone
	private String tel;
	private int score;
	private String status;
	private int role;

	public User() {
	}

	public User(String mail, String pass, int id, String name, String address,
			String city, String tel, int score, String status, int role) {
		this.mail = mail;
		this.pass = pass;
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.tel = tel;
		this.score = score;
		this.status = status;
		this.role = role;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [mail=" + mail + ", pass=" + pass + ", id=" + id
				+ ", name=" + name + ", address=" + address + ", city=" + city
				+ ", tel=" + tel + ", score=" + score + ", status=" + status
				+ ", role=" + role + "]";
	}

}
