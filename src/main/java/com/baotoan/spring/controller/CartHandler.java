package com.baotoan.spring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.OrderDAO;
import com.baotoan.spring.dao.OrderDAOImpl;
import com.baotoan.spring.dao.PaymentDAO;
import com.baotoan.spring.dao.PaymentDAOImpl;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.ProductDAOImpl;
import com.baotoan.spring.entitys.Cart;
import com.baotoan.spring.entitys.DetailOrder;
import com.baotoan.spring.entitys.Order;
import com.baotoan.spring.entitys.Payment;
import com.baotoan.spring.entitys.Product;
import com.baotoan.spring.entitys.User;
import com.baotoan.spring.utils.Constant;
import com.baotoan.spring.utils.GenerateCode;

@Controller
public class CartHandler {
	private ProductDAO productDAO = new ProductDAOImpl();
	private PaymentDAO paymentDAO = new PaymentDAOImpl();
	private OrderDAO orderDAO = new OrderDAOImpl(); 
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/cart", method = RequestMethod.GET)
	public String cartPage(ModelMap model, HttpSession session) {
		Map<String, Object> cartInfor = (HashMap<String, Object>)session.getAttribute("cartInfor");
		if(cartInfor == null || ((List<Cart>)cartInfor.get("listCart")).size() <= 0) {
			return "redirect:index";
		}
		User user = (User)session.getAttribute("user");
		Order order = new Order();
		if(null != user) {
			order.setName(user.getName());
		}
		model.addAttribute("order", order);
		
		List<Payment> listPayment = paymentDAO.getPayments();
		model.addAttribute("payments", listPayment);
		
		Map<String, Object> getListUpsell = productDAO.getProducts("", Constant.GET_BY_TOP_VIEW, Constant.DEFAULT, 4, 1);
		List<Product> listUpsell = (List<Product>)getListUpsell.get("products");
		model.addAttribute("upsells", listUpsell);
		
		return "shopping_cart";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/addCart", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addCart(HttpSession session , @RequestParam(value="id", required = true) int id) {
		Map<String, Object> cartInfor = (HashMap<String, Object>)session.getAttribute("cartInfor");
		try {
		if(null == cartInfor) {
			cartInfor = new HashMap<String, Object>();
			cartInfor.put("listCart", new ArrayList<Cart>());
			cartInfor.put("listRecenty", new ArrayList<Cart>());
			cartInfor.put("totalPay", 0);
			cartInfor.put("sizeCart", 0);
			cartInfor.put("message", "");
		}
		// Process list cart
		Product product = productDAO.getProductById(id);
		List<Cart> listCart = (List<Cart>)cartInfor.get("listCart");
		Cart oldCart = null;
		for(int i = 0; i < listCart.size(); i++) {
			Cart c = listCart.get(i);
			if(c.getId() == id) {
				oldCart = c; 
			}
		}
		if(null == oldCart) {
			listCart.add(new Cart(id, product.getName(), product.getNewPrice(), 1, product.getNewPrice(), product.getUrlImage()));
		} else {
			listCart.remove(oldCart);
			listCart.add(new Cart(id, oldCart.getName(), oldCart.getPrice(), oldCart.getQuantity() + 1, 
					oldCart.getQuantity() * oldCart.getPrice(), oldCart.getImageUrl()));
		}
		updateCartInfor(cartInfor, listCart);
		cartInfor.put("current", product);
		session.setAttribute("cartInfor", cartInfor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartInfor;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/removeCart", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> removeCart(HttpSession session, @RequestParam(value="id", required=true) int id) {
		Map<String, Object> cartInfor = (Map<String, Object>)session.getAttribute("cartInfor");
		List<Cart> listCart = (List<Cart>)cartInfor.get("listCart");
		if(id == -1) {
			listCart.removeAll(listCart);
			cartInfor.put("message", "Bỏ giỏ hàng");
		} else {
			for(int i = 0; i < listCart.size(); i++) {
				if(listCart.get(i).getId() == id) {
					cartInfor.put("current", listCart.get(i));
					listCart.remove(i);
					break;
				}
			}
		}
		updateCartInfor(cartInfor, listCart);
		session.setAttribute("cartInfor", cartInfor);
		return cartInfor;
	}
	
	private int computeTotalPay(List<Cart> listCart) {
		int totalPay = 0;
		for(int i = 0; i < listCart.size(); i++) {
			totalPay += listCart.get(i).getTotalPay();
		}
		return totalPay;
	}
	
	private List<Cart> processRecentyCart(List<Cart> listCart) {
		List<Cart> listRecenty = new ArrayList<Cart>();
		if(listCart.size() >= 2) {
			listRecenty.add(listCart.get(listCart.size() - 1));
			listRecenty.add(listCart.get(listCart.size() - 2));
		} else if(listCart.size() > 0) {
			listRecenty.add(listCart.get(0));
		}
		return listRecenty;
	}
	
	private void updateCartInfor(Map<String, Object> cartInfor, List<Cart> listCart) {
		cartInfor.put("listCart", listCart);
		cartInfor.put("listRecenty", processRecentyCart(listCart));
		cartInfor.put("totalPay", computeTotalPay(listCart));
		cartInfor.put("sizeCart", listCart.size());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/updateCart", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> update(@RequestParam(value="id", required = true) int id, 
			@RequestParam(value="quantity", required=true) int quantity, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> cartInfor = (HashMap<String, Object>)session.getAttribute("cartInfor");
		List<Cart> listCart = (List<Cart>)cartInfor.get("listCart");
		for(int i = 0; i < listCart.size(); i++) {
			Cart cart = listCart.get(i);
			if(cart.getId() == id) {
				if(quantity == 0) {
					listCart.remove(i);
					result.put("pay", 0);
				} else {
					listCart.remove(i);
					listCart.add(new Cart(cart.getId(), cart.getName(), cart.getPrice(), quantity, cart.getPrice() * quantity, cart.getImageUrl()));
					result.put("pay", listCart.get(i).getTotalPay());
				}
				result.put("current", cart);
				break;
			}
		}
		updateCartInfor(cartInfor, listCart);
		result.put("cartSize", listCart.size());
		result.put("totalPay", cartInfor.get("totalPay"));
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/order", method = RequestMethod.POST)
	public String order(@Valid Order order, BindingResult rs, HttpSession session, ModelMap model) {
		List<Payment> listPayment = paymentDAO.getPayments();
		model.addAttribute("payments", listPayment);
		if(rs.hasErrors()) {
			return "shopping_cart";
		}
		User user = (User)session.getAttribute("user");
		Map<String, Object> cartInfor = (HashMap<String, Object>)session.getAttribute("cartInfor");
		List<Cart> listCart = (List<Cart>)cartInfor.get("listCart");
		
		String id = "";
		while(true) {
			id = GenerateCode.generate(10);
			if(orderDAO.getOrderById(id) == null) {
				order.setId(id);
				order.setStatus(1);
				if(null != user) {
					order.setUser(user.getMail());
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				order.setOrderDate(formatter.format(date));
				break;
			}
		}
		orderDAO.addOrder(order);
		
		// Cập nhật danh sách chi tiết cho đơn hàng
		int totalPay = 0;
		if(null != listCart) {
			for(Cart cart : listCart) {
				totalPay += cart.getTotalPay();
				orderDAO.addDetailOrder(new DetailOrder(0, cart.getId(), cart.getQuantity(), cart.getTotalPay(), order.getId()));
			}
		}
		// Cập nhật totalPay cho đơn hàng
		order.setTotalPay(totalPay);
		orderDAO.updateOrder(order);
		// Nếu đơn hàng không có (không cập nhập đc) chi tiết thì xóa
		if(orderDAO.getDetailOrdersByOrderId(order.getId()).size() == 0) {
			orderDAO.deleteOrder(order.getId());
		}
		updateCartInfor(cartInfor, listCart);
		model.addAttribute("message", "Gửi đơn hàng thành công, cảm ơn bạn!");
		return "shopping_cart";
	}
}
