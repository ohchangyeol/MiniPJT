package com.model2.mvc.common;


public class Search {
	
	///Field
	private int curruntPage;
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	
	///Constructor
	public Search() {
	}
	
	//setter method
	public void setCurrentPage(int curruntPage) {this.curruntPage = curruntPage;}
	public void setSearchCondition(String searchCondition) {this.searchCondition = searchCondition;}
	public void setSearchKeyword(String searchKeyword) {this.searchKeyword = searchKeyword;}
	public void setPageSize(int paseSize) {this.pageSize = paseSize;}

	//getter method
	public int getCurrentPage() {return curruntPage;}
	public String getSearchCondition() {return searchCondition;}
	public String getSearchKeyword() {return searchKeyword;}
	public int getPageSize() {return pageSize;}

	// ToString method
	@Override
	public String toString() {
		return "Search [curruntPage=" + curruntPage + ", searchCondition="
				+ searchCondition + ", searchKeyword=" + searchKeyword
				+ ", pageSize=" + pageSize + "]";
	}
}