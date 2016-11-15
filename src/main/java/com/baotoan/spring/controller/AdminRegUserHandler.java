package com.baotoan.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baotoan.spring.dao.RegUserDAO;
import com.baotoan.spring.dao.RegUserDAOImpl;
import com.baotoan.spring.entities.Emailer;
import com.baotoan.spring.entities.RegUser;
import com.baotoan.spring.service.Mailer;

@Controller
@RequestMapping("/mngRegUsers")
public class AdminRegUserHandler {
	private RegUserDAO regDAO = new RegUserDAOImpl();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/show/{currentPage}/", method = RequestMethod.GET)
	public String regUserPage(@PathVariable int currentPage, HttpSession session, ModelMap model, HttpServletRequest request) {
		session.setAttribute("adminCurrentPage", "mngRegUsers");
		
		Map<String, Object> result = regDAO.getAll(20, currentPage);
		List<RegUser> listReg = (List<RegUser>)result.get("regUsers");
		String pagination = (String)result.get("pagination");
		model.addAttribute("regs", listReg);
		model.addAttribute("pagination", pagination.replace("path", request.getContextPath() + "/mngRegUsers/show"));
		model.addAttribute("email", new Emailer("Subject", "ALL", ""));
		
		return "mg_regUsers";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/sendMail", method = RequestMethod.POST)
	public String sendMail(@ModelAttribute("email") Emailer email) {
		String[] mailTo = email.getMailTo().split(",");
		if(email.getMailTo().trim().length() != 0) {
			for(int i = 0; i < mailTo.length; i++) {
				mailTo[i].trim().replaceAll(" ","");
			}
		} else {
			Map<String, Object> result = regDAO.getAll(9999999, 1);
			List<RegUser> listReg = (List<RegUser>)result.get("regUsers");
			mailTo = new String[listReg.size()];
			for(int i = 0; i < mailTo.length; i++) {
				mailTo[i] = listReg.get(i).getMail();
			}
		}
		System.out.println(mailTo.length);
		Mailer mailer = new Mailer();
		mailer.sender(mailTo, email.getSubject(), email.getContent());
		return "redirect:show/1/";
	}
}
