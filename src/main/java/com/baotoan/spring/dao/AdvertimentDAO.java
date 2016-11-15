package com.baotoan.spring.dao;

import java.util.Map;

import com.baotoan.spring.entities.Advertiment;

public interface AdvertimentDAO {
	public boolean addAdvertiment(Advertiment advertiment);
	public boolean updateAdvertiment(Advertiment advertiment);
	public boolean deleteAdvertiment(int id);
	public Advertiment getadAdvertimentById(int id);
	public Map<String, Object> getAdvertiments(int numRecordPerPage, int currentPage);
}
