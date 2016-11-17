package com.baotoan.spring.service;

import java.util.List;
import java.util.Map;

import com.baotoan.spring.entities.DetailProduct;
import com.baotoan.spring.entities.DetailProductGroup;
import com.baotoan.spring.entities.Product;

public interface ProductService {
	public int addProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(int id);
	public Product getProductById(int id);
	
	public boolean addDetailProductGroup(DetailProductGroup detailProductGroup);
	public boolean updateDetailProductGroup(DetailProductGroup detailProductGroup);
	public boolean deleteDetailProductGroup(int id);
	public DetailProductGroup getDetailProductGroupById(int id);
	public List<DetailProductGroup> getDetailProductGroups();
	
	public boolean addDetailProduct(DetailProduct detailProduct);
	public boolean updateDetailProduct(DetailProduct detailProduct);
	public boolean deleteDetailProduct(int id);
	public DetailProduct getDetailProductById(int id);
	public List<DetailProduct> getDetailProductsByGroupId(int detailProductGroupId);
	
	public Map<String, Object> getProducts(Object key, int getBy, int sortBy, int numRecordPerPage, int currentPage);
}
