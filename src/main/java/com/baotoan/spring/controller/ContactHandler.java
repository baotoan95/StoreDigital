package com.baotoan.spring.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baotoan.spring.dao.ContactDAO;
import com.baotoan.spring.entities.Contact;

@Controller
public class ContactHandler {
	@Autowired
	private ContactDAO contactDAO;
	
	@RequestMapping(value="/contact", method = RequestMethod.GET)
	public String contactPage(ModelMap model) {
		model.addAttribute("contact", new Contact());
		return "contact_us";
	}
	
	@RequestMapping(value="/send", method = RequestMethod.POST)
	public String sendContact(@Valid Contact contact, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "contact_us";
		}
		contact.setDate(new Date());
		contact.setStatus(0);
		if(contactDAO.addContact(contact)) {
			model.addAttribute("message", "Gửi thành công, cảm ơn bạn!");
		} else {
			model.addAttribute("message", "Gửi chưa thành công, vui lòng kiểm tra lại!");
		}
		model.addAttribute("contact", contact);
		return "contact_us";
	}
}
