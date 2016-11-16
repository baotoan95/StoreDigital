package com.baotoan.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baotoan.spring.dao.CommentDAO;
import com.baotoan.spring.dao.ContactDAO;
import com.baotoan.spring.dao.OrderDAO;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.UserDAO;
import com.baotoan.spring.entities.User;

@Controller
public class AdminHandler {
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private CommentDAO commnetDAO;
	@Autowired
	private ContactDAO contactDAO;
	
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(null != user) {
			if(user.getRole() == 2) {
				session.setAttribute("adminCurrentPage", "admin");
				
				int totalUser = userDAO.getTotalUserActive();
				int totalProduct = productDAO.getTotalProduct();
				int totalOrder = orderDAO.getTotalOrder();
				int totalOrderNotApproved = orderDAO.getTotalOrderNotApproved();
				session.setAttribute("totalOrderNotApproved", totalOrderNotApproved);
				int totalCmt = commnetDAO.getTotalNewComment();
				int totalContact = contactDAO.getTotalNewContact();
				session.setAttribute("totalUser", totalUser);
				session.setAttribute("totalProduct", totalProduct);
				session.setAttribute("totalOrder", totalOrder);
				session.setAttribute("totalCmt", totalCmt);
				session.setAttribute("totalContact", totalContact);
				return "admin";
			} else {
				return "redirect:index";
			}
		} else {
			return "redirect:login";
		}
	}
	
}
