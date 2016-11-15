package com.baotoan.spring.dao;

import java.util.Map;

import com.baotoan.spring.entities.Contact;

public interface ContactDAO {
	public boolean addContact(Contact contact);
	public boolean deleteContact(int id);
	public boolean updateContact(Contact contact);
	public Contact getContactById(int id);
	public Map<String, Object> getContacts(int numRecordPerPage, int currentPage);
	public int getTotalNewContact();
}
