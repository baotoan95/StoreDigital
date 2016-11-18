package com.baotoan.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.baotoan.spring.entities.Contact;

public class ContactMapper implements RowMapper<Contact> {

	public Contact mapRow(ResultSet rs, int arg1) throws SQLException {
		Contact contact = new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
				rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getInt(9));
		return contact;
	}

}
