package com.baotoan.spring.dao;

import java.util.List;
import java.util.Map;

import com.baotoan.spring.entities.DetailProduct;
import com.baotoan.spring.entities.DetailProductGroup;
import com.baotoan.spring.entities.Product;

public interface ProductDAO {
	public boolean addProduct(Product product);
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
	public boolean deleteDetailProductByProductId(int productId);
	public boolean deleteDetailProductByGroup(int groupId);
	public DetailProduct getDetailProductById(int id);
	public List<DetailProduct> getDetailProductsByGroupId(int detailProductGroupId);
	public List<DetailProduct> getDetailProductsByProductId(int productId);
	public Map<String, Map<String, DetailProduct>> getDetailProduct(int productId);
	
	public Map<String, Object> getProducts(Object key, int getBy, int sortBy, int numRecordPerPage, int currentPage);
	
	public int getTotalProduct();
	public List<Product> getProductsByPostId(int id);
}
