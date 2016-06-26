package com.baotoan.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.ProductDAOImpl;
import com.baotoan.spring.dao.WishDAO;
import com.baotoan.spring.dao.WishDAOImpl;
import com.baotoan.spring.entitys.Product;
import com.baotoan.spring.entitys.User;
import com.baotoan.spring.entitys.Wish;

@Controller
public class WishHandler {
	private WishDAO wishDAO = new WishDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/addWish", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addWish(@RequestParam(value="id", required=true) int id, HttpSession session) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User)session.getAttribute("user");
		Wish wish = new Wish(0, "", id);
		List<Wish> listWish = (List<Wish>)session.getAttribute("listWish");
		result.put("status", "ok");
		result.put("product", productDAO.getProductById(wish.getProductId()));
		// If user not null
		if(null != user) {
			if(!wishDAO.isContains(user.getMail(), id)) {
				wishDAO.addWish(wish = new Wish(0, user.getMail(), id));
			} else {
				result.put("status", "Sản phầm đã có trong danh sách");
				return result;
			}
		} else {
			// If user is null
			if(null == listWish) {
				listWish = new ArrayList<Wish>();
				session.setAttribute("listWish", listWish);
			}
			for(Wish w : listWish) {
				if(w.getProductId() == id) {
					result.put("status", "Sản phầm đã có trong danh sách");
					return result;
				}
			}
			listWish.add(wish);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/delWish", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> deleteWish(@RequestParam(value="id", required = true) int id, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Wish> listWish = (ArrayList<Wish>)session.getAttribute("listWish");
		
		User user = (User)session.getAttribute("user");
		// If user not null
		if(null != user) {
			if(wishDAO.deleteWish(user.getMail(), id)) {
				result.put("status", "ok");
				result.put("product", productDAO.getProductById(id));
				return result;
			}
		} else {
			if(null != listWish) {
				for(int i = 0; i < listWish.size(); i++) {
					Wish w = listWish.get(i);
					if(w.getProductId() == id) {
						listWish.remove(i);
						result.put("status", "ok");
						result.put("product", productDAO.getProductById(id));
						return result;
					}
				}
			}
		}
		result.put("status", "Có lỗi!");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/wish", method = RequestMethod.GET)
	public String wishPage(ModelMap model, HttpSession session) {
		List<Product> listProducts = new ArrayList<Product>();
		List<Wish> listWish = (List<Wish>)session.getAttribute("listWish");
		User user = (User) session.getAttribute("user");
		// If user not null
		if(null != user) {
			listWish = wishDAO.getWishListByUser(user.getMail());
		}
		if(null == listWish) {
			return "redirect:index";
		}
		for(Wish w : listWish) {
			Product pro = productDAO.getProductById(w.getProductId());
			listProducts.add(pro);
		}
		model.addAttribute("listWish", listProducts);
		return "wishlist";
	}
}
