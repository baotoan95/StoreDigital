package com.baotoan.spring.viewsolver;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.baotoan.spring.entities.Product;

public class ProductOutOfStockExcelBuilder extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) model.get("products");
		
		HSSFSheet sheet = workbook.createSheet("Product Out Of Stock Report");
		
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("STT");
		header.createCell(1).setCellValue("Product Name");
		header.createCell(2).setCellValue("Price");
		header.createCell(3).setCellValue("Import Date");
		header.createCell(4).setCellValue("Quantity");
		
		int index = 1;
		for(Product product : products) {
			HSSFRow row = sheet.createRow(index);
			row.createCell(0).setCellValue(index);
			row.createCell(1).setCellValue(product.getName());
			row.createCell(2).setCellValue(product.getNewPrice());
			row.createCell(3).setCellValue(product.getImportDate());
			row.createCell(4).setCellValue(product.getQuantity());
			index++;
		}
	}

}
