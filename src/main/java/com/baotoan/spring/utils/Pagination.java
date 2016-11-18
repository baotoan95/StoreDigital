package com.baotoan.spring.utils;


public class Pagination {
	public static final int GRID_PAGE = 0;
	public static final int TODO_LIST = 1;
	
	private static int currentPage;
	private static int totalPageResult;
	private static int totalSegment;
	private static int currentSegment;
	private static int min;
	private static int max;
	
	public static void main(String[] args) {
		System.out.println(generateHTML(200, 5, 15, 15, Pagination.GRID_PAGE));
	}
	
	public static String generateHTML(int totalRecordResult, int numPageNeedShow, int numRecordPerPage, int currentPage, int type) {
		Pagination.currentPage = currentPage;
		
		totalPageResult = totalRecordResult / numRecordPerPage;
		if(totalRecordResult % numRecordPerPage != 0) {
			totalPageResult++;
		}
		totalSegment = totalPageResult / numPageNeedShow;
		if(totalPageResult % numPageNeedShow != 0) {
			totalSegment++;
		}
		
		if(currentPage > totalPageResult) {
			Pagination.currentPage = totalPageResult;
		}
		
		currentSegment = currentPage / numPageNeedShow;
		if(currentPage % numPageNeedShow != 0) {
			currentSegment++;
		}
		
		min = ((currentSegment - 1) * numPageNeedShow) + 1;
		max = min + numPageNeedShow <= totalPageResult ? min + numPageNeedShow - 1 : totalPageResult;
		
		if(type == Pagination.GRID_PAGE) {
			return htmlForGirdPage();
		} else if(type == Pagination.TODO_LIST) {
			return htmlForToDoList(); 
		}
		return null;
	}
	
	public static String htmlForToDoList() {
		String html = "";

		for(int i = min; i <= max; i++) {
			if(i == currentPage) {
				html += "<li class='active'><a href='path/"+i+"/'>"+i+"</a></li>";
			} else {
				html += "<li><a href='path/"+i+"/'>"+i+"</a></li>";
			}
		}
		
		if(currentSegment == 1) {  // Đầu
			if(currentSegment < totalSegment) {
				html += "<li><a href='path/"+(max + 1)+"/'>&raquo;</a></li>";
			}
		} else if(currentSegment == totalSegment) { // Cuối
			if(currentSegment > 1) {
				html = "<li><a href='path/"+(min-1)+"/'>&laquo;</a></li>" + html;
			}
		} else { // Giữa (tiến + lùi)
			html = 	"<li><a href='path/"+(min-1)+"/'>&laquo;</a></li>" 
					+ html + 
					"<li><a href='path/"+(max+1)+"/'>&raquo;</a></li>";
		}
		return html;
	}
	
	private static String htmlForGirdPage() {
		String html = "";

		for(int i = min; i <= max; i++) {
			if(i == currentPage) {
				html += "<li class='active'><a href='#' onclick=\"submitForm(this, 'page', "+i+")\">"+i+"</a></li>";
			} else {
				html += "<li><a href='#' onclick=\"submitForm(this, 'page', "+i+")\">"+i+"</a></li>";
			}
		}
		
		if(currentSegment == 1) {  // Đầu
			if(currentSegment < totalSegment) {
				html += "<li><a href='#' onclick=\"submitForm(this, 'page', "+(max+1)+")\">&raquo;</a></li>";
			}
		} else if(currentSegment == totalSegment) { // Cuối
			if(currentSegment > 1) {
				html = "<li><a href='#' onclick=\"submitForm(this, 'page', "+(min-1)+")\">&laquo;</a></li>" + html;
			}
		} else { // Giữa (tiến + lùi)
			html = 	"<li><a href='#' onclick=\"submitForm(this, 'page', "+(min-1)+")\">&laquo;</a></li>" 
					+ html + 
					"<li><a href='#' onclick=\"submitForm(this, 'page', "+(max+1)+")\">&raquo;</a></li>";
		}
		return html;
	}
}
