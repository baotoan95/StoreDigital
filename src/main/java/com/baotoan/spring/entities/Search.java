package com.baotoan.spring.entities;

public class Search {
	private Object key;
	private int getBy;
	private int sortBy;
	private int numRecord;
	private int currentPage;

	public Search() {
	}

	public Search(Object key, int getBy, int sortBy, int numRecord,
			int currentPage) {
		super();
		this.key = key;
		this.getBy = getBy;
		this.sortBy = sortBy;
		this.numRecord = numRecord;
		this.currentPage = currentPage;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public int getGetBy() {
		return getBy;
	}

	public void setGetBy(int getBy) {
		this.getBy = getBy;
	}

	public int getSortBy() {
		return sortBy;
	}

	public void setSortBy(int sortBy) {
		this.sortBy = sortBy;
	}

	public int getNumRecord() {
		return numRecord;
	}

	public void setNumRecord(int numRecord) {
		this.numRecord = numRecord;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public String toString() {
		return "Search [key=" + key + ", getBy=" + getBy + ", sortBy=" + sortBy
				+ ", numRecord=" + numRecord + ", currentPage=" + currentPage
				+ "]";
	}

}
