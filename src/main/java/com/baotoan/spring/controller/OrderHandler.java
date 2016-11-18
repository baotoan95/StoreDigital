package com.baotoan.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baotoan.spring.dao.OrderDAO;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.entities.Cart;
import com.baotoan.spring.entities.DetailOrder;
import com.baotoan.spring.entities.Order;
import com.baotoan.spring.entities.Product;
import com.baotoan.spring.entities.User;

@Controller
public class OrderHandler {
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value="/dOrder", method = RequestMethod.GET)
	public String viewOrderDetail(@RequestParam(value="id", required=true) String orderId, ModelMap model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(null == user) {
			return "redirect:index";
		}
		List<DetailOrder> detailOrders = orderDAO.getDetailOrdersByOrderId(orderId);
		List<Cart> result = new ArrayList<Cart>();
		if(null != detailOrders) {
			for(DetailOrder detailOrder : detailOrders) {
				Product product = productDAO.getProductById(detailOrder.getProductId());
				result.add(new Cart(detailOrder.getId(), product.getName(), product.getNewPrice(), detailOrder.getQuantity(),
						detailOrder.getPay(), product.getUrlImage()));
			}
			model.addAttribute("orderId", orderId);
			model.addAttribute("listDetailOrder", result);
		} else {
			orderDAO.deleteOrder(orderId);
		}
		return "detail_order";
	}
	
	@RequestMapping(value="/updateOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateDetailOrder(@RequestParam(value="deId", required = true) int detailOrderId,
						@RequestParam(value="q", required=true) int quantity) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
		// Update detailOrder
		DetailOrder detailOrder = orderDAO.getDetailOrderById(detailOrderId);
		Product product = productDAO.getProductById(detailOrder.getProductId());
		if(quantity > 0) {
			detailOrder.setQuantity(quantity);
			detailOrder.setPay(quantity * product.getNewPrice());
			orderDAO.updateDetailOrder(detailOrder);
			result.put("price", detailOrder.getPay());
			result.put("status", "ok");
		} else {
			orderDAO.deleteDetailOrder(detailOrderId);
			result.put("status", "delete");
		}
		// Update order
		List<DetailOrder> listDetail = orderDAO.getDetailOrdersByOrderId(detailOrder.getOrderId());
		if(null != listDetail) {
			int totalPay = 0;
			for(DetailOrder dor : listDetail) {
				totalPay += dor.getPay();
			}
			Order order = orderDAO.getOrderById(detailOrder.getOrderId());
			order.setTotalPay(totalPay);
			orderDAO.updateOrder(order);
		} else {
			orderDAO.deleteOrder(detailOrder.getOrderId());
		}
		
		result.put("product", product);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "Có lỗi!");
		}		
		return result;
	}
	
	@RequestMapping(value="/delOrder", method = RequestMethod.GET)
	public String delOrder(@RequestParam(value="orId", required = true) String orderId, ModelMap model) {
		if(orderDAO.deleteOrder(orderId)) {
			return "redirect:profile";
		} else {
			model.addAttribute("message", "Có lỗi, vui lòng báo lại chúng tôi!");
			return "detail_order";
		}
	}
	
}
