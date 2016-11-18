package com.baotoan.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.entities.MenuCate;
import com.baotoan.spring.mapper.MenuCateMapper;

@Repository("menuCateDAO")
public class MenuCateDAOImpl extends BaseDAO implements MenuCateDAO {

	public boolean addMenuCate(MenuCate menuCate) {
		String sql = "insert into menu(name,parentId,imageUrl) values(?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{menuCate.getName(), menuCate.getParentId(),
				menuCate.getImageUrl()}) > 0);
	}

	public boolean updateMenuCate(MenuCate menuCate) {
		String sql = "update menu set name=?,parentId=?,imageUrl=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{menuCate.getName(), menuCate.getParentId(), 
			menuCate.getImageUrl(), menuCate.getId()}) > 0);
	}

	public boolean deleteMenuCate(int id) {
		String sql = "delete from menu where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public MenuCate getMenuCateById(int id) {
		String sql = "select * from menu where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new MenuCateMapper());
	}

	public List<MenuCate> getMenuCatesByParentId(int parentId) {
		String sql = "select * from menu where parentId=?";
		return jdbcTemplate.query(sql, new Object[]{parentId}, new MenuCateMapper());
	}

	public List<MenuCate> getAll() {
		String sql = "select * from menu";
		return jdbcTemplate.query(sql, new MenuCateMapper());
	}

}
