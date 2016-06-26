package com.baotoan.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.baotoan.spring.dao.StatusDAO;
import com.baotoan.spring.dao.StatusDAOImpl;
import com.baotoan.spring.entitys.Cart;
import com.baotoan.spring.entitys.DetailOrder;
import com.baotoan.spring.entitys.Order;
import com.baotoan.spring.entitys.Payment;
import com.baotoan.spring.entitys.Product;
import com.baotoan.spring.entitys.Status;
import com.baotoan.spring.utils.OrderConstant;

@Controller
@RequestMapping("/mngOrders")
public class AdminOrderHandler {
	private OrderDAO orderDAO = new OrderDAOImpl();
	private PaymentDAO paymentDAO = new PaymentDAOImpl();
	private StatusDAO statusDAO = new StatusDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{type}/{currentPage}", method = RequestMethod.GET)
	public String orderPage(@PathVariable String type, @PathVariable int currentPage, HttpSession session, ModelMap model) {
		session.setAttribute("adminCurrentPage", "mngOrders");
		
		Map<String, Object> result = null;
		if(type.equalsIgnoreCase("NotApproved")) {
			result = orderDAO.getOrders(OrderConstant.NOT_APPROVED, 20, currentPage);
			model.addAttribute("titlePage", "Danh sách đơn hàng chưa duyệt");
		} else if(type.equalsIgnoreCase("Approved")) {
			result = orderDAO.getOrders(OrderConstant.APPROVED, 20, currentPage);
			model.addAttribute("titlePage", "Danh sách đơn hàng đã duyệt");
		} else if(type.equalsIgnoreCase("Delivered")) {
			result = orderDAO.getOrders(OrderConstant.DELIVERED, 20, currentPage);
			model.addAttribute("titlePage", "Danh sách đơn hàng đã giao");
		} else {
			return "404error";
		}
		List<Order> listOrder = (List<Order>)result.get("orders");
		String pagination = (String)result.get("html");
		model.addAttribute("orders", listOrder);
		model.addAttribute("pagination", pagination);
		
		return "mg_orders";
	}
	
	@RequestMapping(value="/view/{id}/")
	public String viewPage(@PathVariable String id, ModelMap model) {
		Order order = orderDAO.getOrderById(id);
		if(null != order) {
			model.addAttribute("order", order);
			
			List<Payment> payments = paymentDAO.getPayments();
			model.addAttribute("payments", payments);
			
			List<Status> status = statusDAO.getAll();
			model.addAttribute("listStatus", status);
			return "edit_order";
		}
		return "404error";
	}
	
	@RequestMapping(value="/updateOrder", method = RequestMethod.POST)
	public String update(@ModelAttribute Order order, ModelMap model, HttpSession session) {
		System.out.println(order);
		order.setUser((order.getUser().equals("") ? null : order.getUser()));
		
		model.addAttribute("message", "Cập nhật không thành công");
		if(orderDAO.updateOrder(order)) {
			model.addAttribute("message", "Cập nhật thành công");
		}
		List<Payment> payments = paymentDAO.getPayments();
		model.addAttribute("payments", payments);
		
		List<Status> status = statusDAO.getAll();
		model.addAttribute("listStatus", status);
		
		int totalOrderNotApproved = orderDAO.getTotalOrderNotApproved();
		session.setAttribute("totalOrderNotApproved", totalOrderNotApproved);
		return "edit_order";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public @ResponseBody String delete(@RequestParam("id") String id, HttpSession session) {
		orderDAO.deleteDetailOrderByOrderId(id);
		if(orderDAO.deleteOrder(id)) {
			int totalOrderNotApproved = orderDAO.getTotalOrderNotApproved();
			session.setAttribute("totalOrderNotApproved", totalOrderNotApproved);
			return "{\"status\":\"ok\",\"totalOrderNotApproved\":\""+totalOrderNotApproved+"\"}";
		} else {
			return "{\"status\":\"faiture\"}";
		}
	}
	
	@RequestMapping(value="/viewLsDetail/{orderId}/", method = RequestMethod.GET)
	public String viewDetail(@PathVariable String orderId, ModelMap model) {
		Order order = orderDAO.getOrderById(orderId);
		List<DetailOrder> listDetail = orderDAO.getDetailOrdersByOrderId(orderId);
		List<Cart> listDetailOrder = new ArrayList<Cart>();
		for(DetailOrder detail : listDetail) {
			Product product = productDAO.getProductById(detail.getProductId());
			listDetailOrder.add(new Cart(detail.getId(), product.getName(), product.getNewPrice(), detail.getQuantity(), detail.getPay(), product.getUrlImage()));
		}
		model.addAttribute("order", order);
		model.addAttribute("listDetailOrder", listDetailOrder);
		return "detail_orders";
	}
	
	@RequestMapping(value="/updateDetailOrder", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> updateDetailOrder(@RequestParam("id") int id, @RequestParam("quantity") int quantity) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "failure");
		DetailOrder detailOrder = orderDAO.getDetailOrderById(id);
		if(quantity == 0) {
			if(orderDAO.deleteDetailOrder(id)) {
				result.put("status", "delete");
			}
			List<DetailOrder> listDetail = orderDAO.getDetailOrdersByOrderId(detailOrder.getOrderId());
			if(null == listDetail) {
				if(orderDAO.deleteOrder(detailOrder.getOrderId())) {
					result.put("status", "deleteOrder");
				}
			}
		} else {
			detailOrder.setQuantity(quantity);
			detailOrder.setPay(productDAO.getProductById(detailOrder.getProductId()).getNewPrice() * detailOrder.getQuantity());
			if(orderDAO.updateDetailOrder(detailOrder)) {
				result.put("status", "update");
				result.put("pay", detailOrder.getPay());
			}
		}
		return result;
	}
}
