package com.baotoan.spring.dao;

import java.util.List;
import java.util.Map;

import com.baotoan.spring.entities.DetailOrder;
import com.baotoan.spring.entities.Order;

public interface OrderDAO {
	public boolean addOrder(Order order);
	public boolean updateOrder(Order order);
	public boolean deleteOrder(String id);
	public Order getOrderById(String id);
	public List<Order> getOrdersByUser(String email);
	
	public Map<String, Object> getOrders(int type, int numRecordPerPage, int currentPage);
	
	public boolean addDetailOrder(DetailOrder detailOrder);
	public boolean updateDetailOrder(DetailOrder detailOrder);
	public boolean deleteDetailOrder(int id);
	public boolean deleteDetailOrderByOrderId(String orderId);
	public DetailOrder getDetailOrderById(int id);
	public List<DetailOrder> getDetailOrdersByOrderId(String orderId);
	
	//Admin
	public int getTotalOrder();
	public int getTotalOrderNotApproved();
	
}
