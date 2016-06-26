package com.baotoan.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.ImageDAO;
import com.baotoan.spring.dao.ImageDAOImpl;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.ProductDAOImpl;
import com.baotoan.spring.entitys.Product;
import com.baotoan.spring.entitys.Search;
import com.baotoan.spring.utils.Constant;

@Controller
public class SearchHandler {
	private ProductDAO productDAO = new ProductDAOImpl();
	private ImageDAO imageDAO = new ImageDAOImpl();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/autoComplete", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> autoComplete(@RequestParam(value="type", required = true) int type, 
			@RequestParam(value="key", required = true) String key, ModelMap model) {
		List<Product> listResult = new ArrayList<Product>();
		if(type == -1) {
			Map<String, Object> rs =  productDAO.getProducts(key, Constant.SEARCH_DEFAULT, Constant.DEFAULT, 15, 1);
			listResult = (List<Product>)rs.get("products");
		} else {
			String keyWord = type + "-" + key;
			Map<String, Object> rs =  productDAO.getProducts(keyWord, Constant.SEARCH_ADVENCE, Constant.DEFAULT, 15, 1);
			listResult = (List<Product>)rs.get("products");
		}
		
		for(Product pro : listResult) {
			pro.setUrlImage(imageDAO.getAvatarForProduct(pro.getId()).getUrl());
		}
		return listResult;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String searchBox(@RequestParam(value="type", required = true) int type, 
			@RequestParam(value="key", required = true) String key, ModelMap model) {
		List<Product> listResult = new ArrayList<Product>();
		if(type == -1) {
			Map<String, Object> rs =  productDAO.getProducts(key, Constant.SEARCH_DEFAULT, Constant.DEFAULT, 15, 1);
			listResult = (List<Product>)rs.get("products");
			model.addAttribute("search", new Search(key, Constant.SEARCH_DEFAULT, Constant.DEFAULT, 15, 1));
			model.addAttribute("pagination", rs.get("html"));
		} else {
			String keyWord = type + "-" + key;
			Map<String, Object> rs =  productDAO.getProducts(keyWord, Constant.SEARCH_ADVENCE, Constant.DEFAULT, 15, 1);
			listResult = (List<Product>)rs.get("products");
			model.addAttribute("search", new Search(keyWord, Constant.SEARCH_ADVENCE, Constant.DEFAULT, 15, 1));
			model.addAttribute("pagination", rs.get("html"));
		}
		model.addAttribute("result", listResult);
		return "grid";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/searchad", method = RequestMethod.POST)
	public String searchAdvenced(@ModelAttribute("search") Search search, ModelMap model) {
		List<Product> listResult = new ArrayList<Product>();
		Map<String, Object> rs =  productDAO.getProducts(search.getKey(), search.getGetBy(), 
					search.getSortBy(), search.getNumRecord(), search.getCurrentPage());
		listResult = (List<Product>)rs.get("products");
		model.addAttribute("pagination", rs.get("html"));
		model.addAttribute("result", listResult);
		model.addAttribute("search", search);
		return "grid";
	}
	
}
