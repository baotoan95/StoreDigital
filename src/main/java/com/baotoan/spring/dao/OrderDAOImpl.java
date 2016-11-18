package com.baotoan.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.entities.DetailOrder;
import com.baotoan.spring.entities.Order;
import com.baotoan.spring.mapper.DetailOrderMapper;
import com.baotoan.spring.mapper.OrderMapper;
import com.baotoan.spring.utils.OrderConstant;
import com.baotoan.spring.utils.Pagination;

@Repository("orderDAO")
public class OrderDAOImpl extends BaseDAO implements OrderDAO {

	public boolean addOrder(Order order) {
		String sql = "insert into orders(id,name,address,tel,orderDate,deliverDate,paymentId,totalPay,user,sellcoupon, status) values(?,?,?,?,?,?,?,?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{order.getId(), order.getName(), order.getAddress(), order.getTel(), order.getOrderDate(),
				order.getDeliverDate(), order.getPaymentId(), order.getTotalPay(), order.getUser(), order.getSellCoupon(), order.getStatus()}) > 0);
	}

	public boolean updateOrder(Order order) {
		String sql = "update orders set name=?,address=?,tel=?,orderDate=?,deliverDate=?,paymentId=?,totalPay=?,user=?,sellcoupon=?, status=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{order.getName(), order.getAddress(), order.getTel(), order.getOrderDate(),
				order.getDeliverDate(), order.getPaymentId(), order.getTotalPay(), order.getUser(), order.getSellCoupon(),
				order.getStatus(), order.getId()}) > 0);
	}

	public boolean deleteOrder(String id) {
		String sqlDelAllDetail = "delete from order_detail where orderId=?";
		jdbcTemplate.update(sqlDelAllDetail, new Object[] {id});
		String sql = "delete from orders where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public Order getOrderById(String id) {
		String sql = "select * from orders where id=?";
		Order order = null;
		try {
			order = jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderMapper());
		} catch (Exception e) {
			
		}
		return order;
	}

	public List<Order> getOrdersByUser(String mail) {
		String sql = "select * from orders where user=?";
		return jdbcTemplate.query(sql, new Object[]{mail}, new OrderMapper());
	}

	public Map<String, Object> getOrders(int type, int numRecordPerPage, int currentPage) {
		String sql = "select * from orders";
		
		if(type == OrderConstant.APPROVED) {
			sql = "select * from orders where status=2";
		} else if(type == OrderConstant.NOT_APPROVED) {
			sql = "select * from orders where status=1";
		} else if(type == OrderConstant.DELIVERED) {
			sql = "select * from orders where status=3";
		}
		
		List<Order> total = jdbcTemplate.query(sql, new OrderMapper());
		int totalRecordResult = total.size();
		int numPageNeedShow = 5;
		String html = Pagination.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage, Pagination.TODO_LIST);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;
		List<Order> listResult = jdbcTemplate.query(sql, new OrderMapper());

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("orders", listResult);
		result.put("html", html);
		return result;
	}

	public boolean addDetailOrder(DetailOrder detailOrder) {
		String sql = "insert into order_detail(prodId,quantity,pay,orderId) values(?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{detailOrder.getProductId(), detailOrder.getQuantity(), 
				detailOrder.getPay(), detailOrder.getOrderId()}) > 0);
	}

	public boolean updateDetailOrder(DetailOrder detailOrder) {
		String sql = "update order_detail set prodId=?,quantity=?,pay=?,orderId=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{detailOrder.getProductId(), detailOrder.getQuantity(),
				detailOrder.getPay(), detailOrder.getOrderId(), detailOrder.getId()}) > 0);
	}

	public boolean deleteDetailOrder(int id) {
		String sql = "delete from order_detail where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public DetailOrder getDetailOrderById(int id) {
		String sql = "select * from order_detail where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new DetailOrderMapper());
	}

	public List<DetailOrder> getDetailOrdersByOrderId(String orderId) {
		String sql = "select * from order_detail where orderId=?";
		return jdbcTemplate.query(sql, new Object[]{orderId}, new DetailOrderMapper());
	}
	//================ Admin ======================

	@SuppressWarnings("deprecation")
	public int getTotalOrder() {
		String sql = "select count(*) from orders where status=2 and month(orderDate) <= month(now())";
		int total = 0;
		try {
			total = jdbcTemplate.queryForInt(sql);
		} catch (Exception e) { e.printStackTrace(); }
		return total;
	}

	@SuppressWarnings("deprecation")
	public int getTotalOrderNotApproved() {
		String sql = "select count(*) from orders where status=1";
		int total = 0;
		try {
			total = jdbcTemplate.queryForInt(sql);
		} catch (Exception e) { e.printStackTrace(); }
		return total;
	}

	public boolean deleteDetailOrderByOrderId(String orderId) {
		String sql = "delete from order_detail where orderId=?";
		int total = 0;
		try {
			total = jdbcTemplate.update(sql, new Object[]{orderId});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (total > 0);
	}

	
}
