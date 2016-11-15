package com.baotoan.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.ProductDAOImpl;
import com.baotoan.spring.entities.Product;
import com.baotoan.spring.utils.Constant;

@Controller
@RequestMapping("/mngProducts")
public class AdminProductHandler {
	private ProductDAO productDAO = new ProductDAOImpl();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/show/{currentPage}/")
	public String productPage(@PathVariable int currentPage, HttpSession session, ModelMap model, HttpServletRequest request) {
		session.setAttribute("adminCurrentPage", "mngProducts");
		
		Map<String, Object> result = productDAO.getProducts("", -1, Constant.DEFAULT, 15, currentPage);
		List<Product> listProduct = (List<Product>)result.get("products");
		String pagination = (String)result.get("htmlForToDoList");
		model.addAttribute("products", listProduct);
		model.addAttribute("pagination", pagination.replaceAll("path", request.getContextPath() + "/mngProducts/show"));
		
		return "mg_products";
	}
}
