package com.baotoan.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baotoan.spring.entities.Contact;
import com.baotoan.spring.mapper.ContactMapper;
import com.baotoan.spring.utils.Pagination;

public class ContactDAOImpl extends BaseDAO implements ContactDAO {

	public boolean addContact(Contact contact) {
		String sql = "insert into contacts(name,company,address,tel,mail,content,date,status) values(?,?,?,?,?,?,?,?)";
		return (jdbcTemplate.update(sql, new Object[]{contact.getName(), contact.getCompany(), contact.getAddress(),
				contact.getTel(), contact.getMail(), contact.getContent(), contact.getDate(), contact.getStatus()}) > 0);
	}

	public boolean deleteContact(int id) {
		String sql = "delete * from contacts where id=?";
		return (jdbcTemplate.update(sql, new Object[]{id}) > 0);
	}

	public Contact getContactById(int id) {
		String sql = "select * from contacts where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ContactMapper());
	}

	public Map<String, Object> getContacts(int numRecordPerPage, int currentPage) {
		String sql = "select * from contacts";
		
		List<Contact> total = jdbcTemplate.query(sql, new ContactMapper());
		int totalRecordResult = total.size();
		int numPageNeedShow = 5;
		String htmlForToDoList = Pagination.generateHTML(totalRecordResult, numPageNeedShow, numRecordPerPage, currentPage, Pagination.TODO_LIST);
		
		int recordStart = ((currentPage - 1) * numRecordPerPage);
		if(numRecordPerPage > totalRecordResult) {
			recordStart = 0;
			currentPage = 1;
		}
		sql += " LIMIT "+ recordStart +", "+ numRecordPerPage;
		List<Contact> listResult = jdbcTemplate.query(sql, new ContactMapper());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("contacts", listResult);
		result.put("pagination", htmlForToDoList);
		
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public int getTotalNewContact() {
		String sql = "select count(id) from contacts where status=0";
		int total = 0;
		try {
			total = jdbcTemplate.queryForInt(sql);
		} catch (Exception e) { }
		return total;
	}

	public boolean updateContact(Contact contact) {
		String sql = "update contacts set name=?,company=?,address=?,tel=?,mail=?,content=?,"
				+ "date=?,status=? where id=?";
		return (jdbcTemplate.update(sql, new Object[]{contact.getName(), contact.getCompany(), contact.getAddress(),
				contact.getTel(), contact.getMail(), contact.getContent(), contact.getDate(), contact.getStatus(), contact.getId()}) > 0);
	}

}
