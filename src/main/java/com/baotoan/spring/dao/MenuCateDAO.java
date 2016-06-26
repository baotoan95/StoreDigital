package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entitys.MenuCate;

public interface MenuCateDAO {
	public boolean addMenuCate(MenuCate menuCate);
	public boolean updateMenuCate(MenuCate menuCate);
	public boolean deleteMenuCate(int id);
	public MenuCate getMenuCateById(int id);
	public List<MenuCate> getMenuCatesByParentId(int parentId);
	public List<MenuCate> getAll();
}
