package com.baotoan.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baotoan.spring.dao.CommentDAO;
import com.baotoan.spring.dao.CommentDAOImpl;
import com.baotoan.spring.dao.ContactDAO;
import com.baotoan.spring.dao.ContactDAOImpl;
import com.baotoan.spring.dao.OrderDAO;
import com.baotoan.spring.dao.OrderDAOImpl;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.ProductDAOImpl;
import com.baotoan.spring.dao.UserDAO;
import com.baotoan.spring.dao.UserDAOImpl;
import com.baotoan.spring.entitys.User;

@Controller
public class AdminHandler {
	private OrderDAO orderDAO = new OrderDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();
	private CommentDAO commnetDAO = new CommentDAOImpl();
	private ContactDAO contactDAO = new ContactDAOImpl();
	
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
