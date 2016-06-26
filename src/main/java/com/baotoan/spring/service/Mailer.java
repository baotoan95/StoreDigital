package com.baotoan.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailer")
public class Mailer {
	@Autowired
	private MailSender mailSender;
	
	public boolean sender(String[] toAddress, String subject, String messageBody) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("baotoan.95@gmail.com");
		message.setTo(toAddress);
		message.setSubject(subject);
		message.setText(messageBody);
		System.out.println(message);
		try {
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
