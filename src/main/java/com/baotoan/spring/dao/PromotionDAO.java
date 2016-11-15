package com.baotoan.spring.dao;

import java.util.List;
import java.util.Map;

import com.baotoan.spring.entities.Promotion;
import com.baotoan.spring.entities.PromotionDetail;

public interface PromotionDAO {
	public boolean addPromotion(Promotion promotion);
	public boolean updatePromotion(Promotion promotion);
	public boolean deletePromotion(int id);
	public Promotion getPromotionById(int id);
	public Map<String, Object> getPromotions(int numRecordPerPage, int currentPage);
	
	public boolean addPromotionDetail(PromotionDetail promotionDetail);
	public boolean updatePromotionDetail(PromotionDetail promotionDetail);
	public boolean deletePromotionDetail(int id);
	public boolean deletePromotionDetailsByPromotionId(int id);
	public PromotionDetail getPromotionDetailById(int id);
	public Map<String, Object> getPromotionDetailByPromotionId(int promotionId, int numRecordPerPage, int currentPage);
	public List<PromotionDetail> getPromotionDetailByProductId(int productId);
	
}
