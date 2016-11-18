package com.baotoan.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baotoan.spring.dao.ContactDAO;
import com.baotoan.spring.entities.Contact;

@Controller
@RequestMapping("/mngContacts")
public class AdminContactHandler {
	@Autowired
	private ContactDAO contactDAO;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/show/{currentPage}/", method = RequestMethod.GET)
	public String contactPage(@PathVariable int currentPage, HttpSession session, ModelMap model) {
		session.setAttribute("adminCurrentPage", "mngContacts");
		
		Map<String, Object> result = contactDAO.getContacts(20, currentPage); 
		List<Contact> listContact = (List<Contact>)result.get("contacts"); 
		String pagination = (String)result.get("pagination");
		model.addAttribute("contacts", listContact);
		model.addAttribute("pagination", pagination);
		
		return "mg_contacts";
	}
	
	@RequestMapping(value="/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable int id, ModelMap model) {
		model.addAttribute("contact", contactDAO.getContactById(id));
		return "edit_contact";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	    format.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
	
	@RequestMapping(value="/updateContact", method = RequestMethod.POST)
	public String updateContact(@ModelAttribute("contact") Contact contact, ModelMap model, HttpSession session) {
		if(contactDAO.updateContact(contact)) {
			model.addAttribute("message", "Cập nhật thành công");
		} else {
			model.addAttribute("message", "Cập nhật không thành công");
		}
		int totalContact = contactDAO.getTotalNewContact();
		session.setAttribute("totalContact", totalContact);
		return "edit_contact";
	}
}
