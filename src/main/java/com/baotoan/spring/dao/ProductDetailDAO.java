package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entities.DetailProductGroup;
import com.baotoan.spring.entities.ProductDetailByGroup;

public interface ProductDetailDAO {
	public ProductDetailByGroup getDetailByGroupById(int id);
	public boolean addDetail(ProductDetailByGroup productDetailByGroup);
	public boolean updateDetail(ProductDetailByGroup productDetailByGroup);
	public List<ProductDetailByGroup> getAllProductDetailByGroup();
	public List<ProductDetailByGroup> getAllProductDetailByGroupId(int groupId);
	public boolean deleteDetailByGroup(int id);
	public boolean deleteDetailByGroupByGroupId(int groupId);
	
	public DetailProductGroup getDetailGroupById(int id);
	public boolean addDetailGroup(DetailProductGroup detailProductGroup);
	public boolean updateDetailGroup(DetailProductGroup detailProductGroup);
	public List<DetailProductGroup> getAllDetailGroup();
	public boolean deleteDetailGroup(int id);
}
