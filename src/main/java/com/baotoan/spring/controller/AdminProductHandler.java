package com.baotoan.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.entities.Product;
import com.baotoan.spring.utils.Constant;

@Controller
@RequestMapping("/mngProducts")
public class AdminProductHandler {
	@Autowired
	private ProductDAO productDAO;
	
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
	
	@RequestMapping(value = "/del", method = RequestMethod.DELETE)
	@ResponseBody
	public String delProduct(@ModelAttribute(value = "id") int id) {
		productDAO.deleteProduct(id);
		return "{\"\":\"\"}";
	}
	
	@RequestMapping(value = "/edit/{proId}")
	public String editProduct(@PathVariable int proId, ModelMap model) {
		Product product = productDAO.getProductById(proId);
		
		// Get list category
		
		
		model.addAttribute("title", "Cập nhật thông tin sản phẩm");
		model.addAttribute("product", product);
		return "edit_product";
	}
}
