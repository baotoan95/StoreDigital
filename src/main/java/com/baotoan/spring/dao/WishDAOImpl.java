package com.baotoan.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.entities.Wish;
import com.baotoan.spring.mapper.WishMapper;

@Repository("wishDAO")
public class WishDAOImpl extends BaseDAO implements WishDAO {
	public boolean addWish(Wish wish) {
		String sql = "insert into wishlist(id,user,productId) values(?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{wish.getId(), wish.getUser(), wish.getProductId()}) > 0);
	}

	public boolean updateWish(Wish wish) {
		String sql = "update wishlist set user=?,productId=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{wish.getUser(), wish.getProductId(), wish.getId()}) > 0);
	}

	public boolean deleteWish(String user, int productId) {
		String sql = "delete from wishlist where user=? and productId=?";
		return (jdbcTemplate.update(sql, new Object[]{user, productId}) > 0);
	}

	public Wish getWishById(int id) {
		String sql = "select * from wishlist where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new WishMapper());
	}

	public List<Wish> getWishListByUser(String email) {
		String sql = "select * from wishlist where user=?";
		return jdbcTemplate.query(sql, new Object[]{email}, new WishMapper());
	}

	@SuppressWarnings("deprecation")
	public boolean isContains(String user, int productID) {
		String sql = "select count(*) from wishlist where user=? and productId=?";
		return (jdbcTemplate.queryForInt(sql, new Object[]{user, productID}) > 0);
	}

}
