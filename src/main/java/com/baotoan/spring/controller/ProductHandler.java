package com.baotoan.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baotoan.spring.dao.ImageDAO;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.entities.Image;
import com.baotoan.spring.entities.Product;
import com.baotoan.spring.entities.Search;
import com.baotoan.spring.utils.Constant;

@Controller
public class ProductHandler {
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ImageDAO imageDAO;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String viewDetail(@RequestParam(value="id", required=true) int id, ModelMap model) {
		Product product = productDAO.getProductById(id);
		if(null == product) {
			return "404error";
		}
		product.setDetail(productDAO.getDetailProduct(product.getId()));
		List<Image> listImage = imageDAO.getImagesByProductId(id);
		model.addAttribute("product", product);
		model.addAttribute("images", listImage);
		
		Map<String, Object> getListRelated = productDAO.getProducts(product.getCateId(), Constant.GET_BY_RELATED, Constant.DEFAULT, 20, 1);
		List<Product> listRelated = (List<Product>)getListRelated.get("products");
		model.addAttribute("relateds", listRelated);
		
		Map<String, Object> getListUpSell = productDAO.getProducts(product.getCateId(), Constant.GET_BY_UPSELL, Constant.DEFAULT, 20, 1);
		List<Product> listUpSell = (List<Product>)getListUpSell.get("products");
		model.addAttribute("upsells", listUpSell);
		return "product_detail";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/show/{id}/", method = RequestMethod.GET)
	public String viewProducts(@PathVariable(value="id") int id, ModelMap model) {
		System.out.println("dsfsdf");
		List<Product> listResult = new ArrayList<Product>();
		Search search = new Search(id+"- ", Constant.SEARCH_ADVENCE, Constant.DEFAULT, 15, 1);
		Map<String, Object> rs =  productDAO.getProducts(search.getKey(), search.getGetBy(), 
					search.getSortBy(), search.getNumRecord(), search.getCurrentPage());
		listResult = (List<Product>)rs.get("products");
		model.addAttribute("pagination", rs.get("html"));
		model.addAttribute("result", listResult);
		model.addAttribute("search", search);
		System.out.println("adsfsdfsdf");
		return "grid";
	}
	
}
