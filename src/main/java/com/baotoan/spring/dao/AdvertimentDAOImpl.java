package com.baotoan.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baotoan.spring.entities.Advertiment;
import com.baotoan.spring.mapper.AdvertimentMapper;
import com.baotoan.spring.utils.Pagination;

@Repository("advertimentDAO")
public class AdvertimentDAOImpl extends BaseDAO implements AdvertimentDAO {

	public boolean addAdvertiment(Advertiment advertiment) {
		String sql = "insert into advertiments(name,imageUrl,status,advertiments.describe) values(?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{advertiment.getName(), advertiment.getImageUrl(),
				advertiment.getStatus(), advertiment.getDescribe()}) > 0);
	}

	public boolean updateAdvertiment(Advertiment advertiment) {
		String sql = "update advertiments set name=?,imageUrl=?,status=?,advertiments.describe=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{advertiment.getName(), advertiment.getImageUrl(),
				advertiment.getStatus(), advertiment.getDescribe(), advertiment.getId()}) > 0);
	}

	public boolean deleteAdvertiment(int id) {
		String sql = "delete from advertiments where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public Advertiment getadAdvertimentById(int id) {
		String sql = "select * from advertiments where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new AdvertimentMapper());
	}

	public Map<String, Object> getAdvertiments(int numRecordPerPage, int currentPage) {
		String sql = "select * from advertiments";
		
		List<Advertiment> total = jdbcTemplate.query(sql, new AdvertimentMapper());
		int totalRecordResult = total.size();
		int numPageNeedShow = 5;
		String htmlForToDoList = Pagination.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage, Pagination.TODO_LIST);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;
		List<Advertiment> listResult = jdbcTemplate.query(sql, new AdvertimentMapper());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("adverts", listResult);
		result.put("pagination", htmlForToDoList);
		
		return result;
	}

}
