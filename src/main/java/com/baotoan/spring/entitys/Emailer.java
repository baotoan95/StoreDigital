package com.baotoan.spring.entitys;

public class Emailer {
	private String subject;
	private String mailTo;
	private String content;

	public Emailer() {
	}

	public Emailer(String subject, String mailTo, String content) {
		this.subject = subject;
		this.mailTo = mailTo;
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
