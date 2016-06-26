package com.baotoan.spring.entitys;

public class RegUser {
	private int id;
	private String mail;

	public RegUser() {
	}

	public RegUser(int id, String mail) {
		this.id = id;
		this.mail = mail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "mail [id=" + id + ", mail=" + mail + "]";
	}

}
