package com.baotoan.spring.impl;

import java.util.ArrayList;
import java.util.List;

import com.baotoan.spring.dao.BaseDAO;
import com.baotoan.spring.dao.ProductDetailDAO;
import com.baotoan.spring.entities.DetailProductGroup;
import com.baotoan.spring.entities.ProductDetailByGroup;
import com.baotoan.spring.mapper.DetailProductGroupMapper;
import com.baotoan.spring.mapper.ProductDetailByGroupMapper;

public class ProductDetailDAOImpl extends BaseDAO implements ProductDetailDAO {
	
	public List<ProductDetailByGroup> getAllProductDetailByGroup() {
		String sql = "select * from detail_product_by_group";
		return jdbcTemplate.query(sql, new ProductDetailByGroupMapper());
	}

	public List<ProductDetailByGroup> getAllProductDetailByGroupId(int groupId) {
		String sql = "select * from detail_product_by_group where groupId=?";
		return jdbcTemplate.query(sql, new String[] {String.valueOf(groupId)}, new ProductDetailByGroupMapper());
	}

	public List<DetailProductGroup> getAllDetailGroup() {
		String sql = "select * from prod_group_detail";
		List<DetailProductGroup> detailGroups = new ArrayList<DetailProductGroup>();
		ArrayList<DetailProductGroup> dgs = (ArrayList<DetailProductGroup>) jdbcTemplate.query(sql, new String[]{}, new DetailProductGroupMapper());
		detailGroups.addAll(dgs);
		return detailGroups;
	}

	public boolean addDetail(ProductDetailByGroup productDetailByGroup) {
		String sql = "insert into detail_product_by_group (name, groupId) values (?,?)";
		return jdbcTemplate.update(sql, new Object[] {productDetailByGroup.getName(), productDetailByGroup.getGroupId()}) > 0;
	}

	public boolean updateDetail(ProductDetailByGroup productDetailByGroup) {
		String sql = "update detail_product_by_group set name=?, groupId=? where id=?";
		return jdbcTemplate.update(sql, new Object[] {productDetailByGroup.getName(), productDetailByGroup.getGroupId(), productDetailByGroup.getId()}) > 0;
	}

	public boolean addDetailGroup(DetailProductGroup detailProductGroup) {
		String sql = "insert into prod_group_detail(name) values (?)";
		return jdbcTemplate.update(sql, new Object[] {detailProductGroup.getName()}) > 0;
	}

	public boolean updateDetailGroup(DetailProductGroup detailProductGroup) {
		String sql = "update prod_group_detail set name=? where id=?";
		return jdbcTemplate.update(sql, new Object[] {detailProductGroup.getName(), detailProductGroup.getId()}) > 0;
	}

	public ProductDetailByGroup getDetailByGroupById(int id) {
		String sql = "select * from detail_product_by_group where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new ProductDetailByGroupMapper());
	}

	public DetailProductGroup getDetailGroupById(int id) {
		String sql = "select * from prod_detail_group where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new DetailProductGroupMapper());
	}

}
