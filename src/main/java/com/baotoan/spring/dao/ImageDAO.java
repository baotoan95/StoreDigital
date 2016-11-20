package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entities.Image;

public interface ImageDAO {
	public boolean addImage(Image image);
	public boolean updateImage(Image image);
	public boolean deleteImage(int id);
	public boolean deleteImageByProductId(int productId);
	public Image getImageById(int id);
	public Image getAvatarForProduct(int productId);
	public List<Image> getImagesByProductId(int productId);
}
