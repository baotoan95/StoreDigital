package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entities.Wish;

public interface WishDAO {
	public boolean addWish(Wish wish);
	public boolean updateWish(Wish wish);
	public boolean deleteWish(String user, int productId);
	public Wish getWishById(int id);
	public List<Wish> getWishListByUser(String email);
	public boolean isContains(String user, int productID);
}
