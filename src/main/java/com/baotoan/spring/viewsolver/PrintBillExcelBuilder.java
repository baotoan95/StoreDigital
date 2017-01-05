package com.baotoan.spring.viewsolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.baotoan.spring.dao.OrderDAO;
import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.impl.OrderDAOImpl;
import com.baotoan.spring.dao.impl.ProductDAOImpl;
import com.baotoan.spring.entities.DetailOrder;
import com.baotoan.spring.entities.Order;

public class PrintBillExcelBuilder extends AbstractExcelView {
	private OrderDAO orderDAO;
	private ProductDAO productDAO;

	public PrintBillExcelBuilder() {
		this.orderDAO = new OrderDAOImpl();
		this.productDAO = new ProductDAOImpl();
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Order order = (Order) model.get("order");
		
		HSSFSheet sheet = workbook.createSheet("Bill");
		
		HSSFRow top = sheet.createRow(0);
		top.createCell(5).setCellValue("Đơn Hàng");;
		
		HSSFRow name = sheet.createRow(3);
		name.createCell(1).setCellValue("Tên khách hàng");
		name.createCell(3).setCellValue(order.getName());
		HSSFRow addressReceive = sheet.createRow(4);
		addressReceive.createCell(1).setCellValue("Địa chỉ nhận hàng");
		addressReceive.createCell(3).setCellValue(order.getAddress());
		HSSFRow phone = sheet.createRow(5);
		phone.createCell(1).setCellValue("Điện thoại");
		phone.createCell(3).setCellValue(order.getTel());
		HSSFRow orderDate = sheet.createRow(6);
		orderDate.createCell(1).setCellValue("Ngày đặt hàng");
		orderDate.createCell(3).setCellValue(order.getOrderDate());
		HSSFRow deliverDate = sheet.createRow(7);
		deliverDate.createCell(1).setCellValue("Ngày giao hàng");
		deliverDate.createCell(3).setCellValue(order.getDeliverDate());
		
		HSSFRow title = sheet.createRow(10);
		title.createCell(1).setCellValue("Chi tiết đơn hàng");
		
		HSSFRow header = sheet.createRow(12);
		header.createCell(0).setCellValue("STT");
		header.createCell(1).setCellValue("Tên sản phẩm");
		header.createCell(2).setCellValue("Số lượng");
		header.createCell(3).setCellValue("Thành tiền");
		
		List<DetailOrder> detailOrders = orderDAO.getDetailOrdersByOrderId(order.getId());
		int i = 13; // next row index
		for(DetailOrder detailOrder : detailOrders) {
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(i - 13);
			row.createCell(1).setCellValue(productDAO.getProductById(detailOrder.getProductId()).getName());
			row.createCell(2).setCellValue(detailOrder.getQuantity());
			row.createCell(3).setCellValue(detailOrder.getPay());
			i++;
		}
		
		HSSFRow totalPay = sheet.createRow(i);
		totalPay.createCell(1).setCellValue("Thành tiền");
		totalPay.createCell(2).setCellValue(order.getTotalPay() + " vnd");
	}
	
}
