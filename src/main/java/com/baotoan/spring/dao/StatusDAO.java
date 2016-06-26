package com.baotoan.spring.dao;

import java.util.List;

import com.baotoan.spring.entitys.Status;

public interface StatusDAO {
	public Status getStatusById(int id);
	public List<Status> getAll();
}
