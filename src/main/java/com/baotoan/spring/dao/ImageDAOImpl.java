package com.baotoan.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.entities.Image;
import com.baotoan.spring.mapper.ImageMapper;

@Repository("imageDAO")
public class ImageDAOImpl extends BaseDAO implements ImageDAO {

	public boolean addImage(Image image) {
		String sql = "insert into images(name,url,prodId,isAvatar) values(?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{image.getName(), image.getUrl(), image.getProductId(), 
				image.isAvatar()}) > 0);
	}

	public boolean updateImage(Image image) {
		String sql = "update images set name=?,url=?,prodId=?,isAvatar=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{image.getName(), image.getUrl(), image.getProductId(), 
				image.isAvatar(), image.getId()}) > 0);
	}

	public boolean deleteImage(int id) {
		String sql = "delete * from images where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}, new ImageMapper()) > 0);
	}

	public Image getImageById(int id) {
		String sql = "select * from images where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ImageMapper());
	}

	public Image getAvatarForProduct(int productId) {
		String sql = "select * from images where prodId=? and isAvatar=1";
		return jdbcTemplate.queryForObject(sql, new Object[]{productId}, new ImageMapper());
	}

	public List<Image> getImagesByProductId(int productId) {
		String sql = "select * from images where prodId=?";
		return jdbcTemplate.query(sql, new Object[]{productId}, new ImageMapper());
	}

}
