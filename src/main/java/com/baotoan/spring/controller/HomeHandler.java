package com.baotoan.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baotoan.spring.dao.AdvertimentDAO;
import com.baotoan.spring.dao.AdvertimentDAOImpl;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.ProductDAOImpl;
import com.baotoan.spring.entitys.Advertiment;
import com.baotoan.spring.entitys.Product;
import com.baotoan.spring.utils.Constant;

@Controller
@RequestMapping("/")
public class HomeHandler {
	private ProductDAO productDAO = new ProductDAOImpl();
	private AdvertimentDAO adverDAO = new AdvertimentDAOImpl();

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String indexPage(ModelMap model) {
		try {
		// Advertiments
		Map<String, Object> result = adverDAO.getAdvertiments(20, 1);
		List<Advertiment> listAdver = (List<Advertiment>)result.get("adverts");
		model.addAttribute("advers", listAdver);
		// Best selling
		Map<String, Object> getListBestSell = productDAO.getProducts("", Constant.GET_BY_BEST_SELL, Constant.DEFAULT, 10, 1);
		List<Product> listBestSell = (List<Product>)getListBestSell.get("products");
		model.addAttribute("bestsell", listBestSell);
		// Latest
		Map<String, Object> getListLatest = productDAO.getProducts("", Constant.GET_BY_LATEST, Constant.DEFAULT, 10, 1);
		List<Product> listLatest = (List<Product>)getListLatest.get("products");
		model.addAttribute("latest", listLatest);
		// Views
		Map<String, Object> getListViews = productDAO.getProducts("", Constant.GET_BY_TOP_VIEW, Constant.DEFAULT, 10, 1);
		List<Product> listViews = (List<Product>)getListViews.get("products");
		model.addAttribute("views", listViews);
		// Random
		Map<String, Object> getListRand = productDAO.getProducts("", -1, Constant.DEFAULT, 10, 1);
		List<Product> listRand = (List<Product>)getListRand.get("products");
		model.addAttribute("rands", listRand);
		// Top reviews
		Map<String, Object> getListTopReviews = productDAO.getProducts("", Constant.GET_BY_TOP_REVIEW, Constant.DEFAULT, 4, 1);
		List<Product> listTopReviews = (List<Product>)getListTopReviews.get("products");
		model.addAttribute("topReviews", listTopReviews);
		// Top Latest
		Map<String, Object> getListTopLatest = productDAO.getProducts("", Constant.GET_BY_LATEST, Constant.DEFAULT, 4, 1);
		List<Product> listTopLatest = (List<Product>)getListTopLatest.get("products");
		model.addAttribute("topLatest", listTopLatest);
		// Promotion
		Map<String, Object> getListPromotion = productDAO.getProducts("", Constant.GET_BY_LATEST, Constant.DEFAULT, 20, 1);
		List<Product> listPromotion = (List<Product>)getListPromotion.get("products");
		model.addAttribute("promotions", listPromotion);
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
		return "index";
	}

}
