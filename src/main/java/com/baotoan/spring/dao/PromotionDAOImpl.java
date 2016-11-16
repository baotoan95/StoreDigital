package com.baotoan.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.entities.Promotion;
import com.baotoan.spring.entities.PromotionDetail;
import com.baotoan.spring.mapper.PromotionDetailMapper;
import com.baotoan.spring.mapper.PromotionMapper;
import com.baotoan.spring.utils.Pagination;

@Repository("promotionDAO")
public class PromotionDAOImpl extends BaseDAO implements PromotionDAO {

	public boolean addPromotion(Promotion promotion) {
		String sql = "insert into promotions(start,end,name,imgUrl) values(?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{promotion.getStart(), promotion.getEnd(), 
				promotion.getName(), promotion.getImageUrl()}) > 0);
	}

	public boolean updatePromotion(Promotion promotion) {
		String sql = "update promotions set start=?,end=?,name=?,imageurl=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{promotion.getStart(), promotion.getEnd(), promotion.getName(), 
				promotion.getImageUrl(), promotion.getId()}) > 0);
	}

	public boolean deletePromotion(int id) {
		String sql = "delete from promotions where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public Promotion getPromotionById(int id) {
		String sql = "select * from promotions where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PromotionMapper());
	}

	public Map<String, Object> getPromotions(int numRecordPerPage, int currentPage) {
		String sql = "select * from promotions";
		
		List<Promotion> total = jdbcTemplate.query(sql, new PromotionMapper());
		int totalRecordResult = total.size();
		int numPageNeedShow = 5;
		String htmlForToDoList = Pagination.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage, Pagination.TODO_LIST);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;
		List<Promotion> listResult = jdbcTemplate.query(sql, new PromotionMapper());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("promotions", listResult);
		result.put("htmlForToDoList", htmlForToDoList);
		
		return result;
	}

	public boolean addPromotionDetail(PromotionDetail promotionDetail) {
		String sql = "insert into promotion_detail(detail,promotion_detail.describe,promotionId) values(?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{promotionDetail.getDetail(),
				promotionDetail.getDescribe(), promotionDetail.getPromotionId()}) > 0);
	}

	public boolean updatePromotionDetail(PromotionDetail promotionDetail) {
		String sql = "update promotion_detail set detail=?,promotion_detail.describe=?,promotionId=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{promotionDetail.getDetail(), promotionDetail.getDescribe(),
				promotionDetail.getPromotionId(), promotionDetail.getId()}) > 0);
	}

	public boolean deletePromotionDetail(int id) {
		String sql = "delete from promotion_detail where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public PromotionDetail getPromotionDetailById(int id) {
		String sql = "select * from promotion_detail where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PromotionDetailMapper());
	}

	public Map<String, Object> getPromotionDetailByPromotionId(int promotionId, int numRecordPerPage, int currentPage) {
		String sql = "select * from promotion_detail where promotionId=?";
		
		List<PromotionDetail> total = jdbcTemplate.query(sql, new Object[]{promotionId}, new PromotionDetailMapper());
		int totalRecordResult = total.size();
		int numPageNeedShow = 5;
		String htmlForToDoList = Pagination.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage, Pagination.TODO_LIST);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;
		List<PromotionDetail> listResult = jdbcTemplate.query(sql, new Object[]{promotionId}, new PromotionDetailMapper());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("promotions", listResult);
		result.put("htmlForToDoList", htmlForToDoList);
		
		return result;
	}

	public List<PromotionDetail> getPromotionDetailByProductId(int productId) {
		String sql = "select * from promotion_detail where productId=?";
		return jdbcTemplate.query(sql,  new Object[]{productId}, new PromotionDetailMapper());
	}

	public boolean deletePromotionDetailsByPromotionId(int id) {
		String sql = "delete from promotion_detail where promotionId=?";
		try {
			int result = jdbcTemplate.update(sql, new Object[]{id});
			return result > 0;
		} catch (Exception e) {
			return false;
		}
	}

}
