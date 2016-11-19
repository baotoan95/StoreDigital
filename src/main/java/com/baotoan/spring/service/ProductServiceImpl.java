package com.baotoan.spring.service;

import java.util.List;
import java.util.Map;

import com.baotoan.spring.dao.ProductDAO;
import com.baotoan.spring.dao.impl.ProductDAOImpl;
import com.baotoan.spring.entities.DetailProduct;
import com.baotoan.spring.entities.DetailProductGroup;
import com.baotoan.spring.entities.Product;

public class ProductServiceImpl implements ProductService {
	private ProductDAO productDAO = new ProductDAOImpl();

	public int addProduct(Product product) {
		return productDAO.addProduct(product);
	}

	public boolean updateProduct(Product product) {
		return productDAO.updateProduct(product);
	}

	public boolean deleteProduct(int id) {
		return productDAO.deleteDetailProductByProductId(id) && productDAO.deleteProduct(id);
	}

	public Product getProductById(int id) {
		return productDAO.getProductById(id);
	}

	public boolean addDetailProductGroup(DetailProductGroup detailProductGroup) {
		return productDAO.addDetailProductGroup(detailProductGroup);
	}

	public boolean updateDetailProductGroup(
			DetailProductGroup detailProductGroup) {
		return productDAO.updateDetailProductGroup(detailProductGroup);
	}

	public boolean deleteDetailProductGroup(int id) {
		return productDAO.deleteDetailProductByGroup(id) && productDAO.deleteDetailProductGroup(id);
	}

	public DetailProductGroup getDetailProductGroupById(int id) {
		return productDAO.getDetailProductGroupById(id);
	}

	public List<DetailProductGroup> getDetailProductGroups() {
		return productDAO.getDetailProductGroups();
	}

	public boolean addDetailProduct(DetailProduct detailProduct) {
		return productDAO.addDetailProduct(detailProduct);
	}

	public boolean updateDetailProduct(DetailProduct detailProduct) {
		return productDAO.updateDetailProduct(detailProduct);
	}

	public boolean deleteDetailProduct(int id) {
		return productDAO.deleteDetailProduct(id);
	}

	public DetailProduct getDetailProductById(int id) {
		return productDAO.getDetailProductById(id);
	}

	public List<DetailProduct> getDetailProductsByGroupId(int detailProductGroupId) {
		return productDAO.getDetailProductsByGroupId(detailProductGroupId);
	}

	public Map<String, Object> getProducts(Object key, int getBy, int sortBy, int numRecordPerPage, int currentPage) {
		return productDAO.getProducts(key, getBy, sortBy, numRecordPerPage, currentPage);
	}

	

}
