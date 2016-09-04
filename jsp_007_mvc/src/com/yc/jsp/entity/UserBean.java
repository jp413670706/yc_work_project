package com.yc.jsp.entity;

import java.util.List;
import java.util.Map;

public class UserBean {
	private Integer currPage;
	private Integer pageSize;
	private Integer totalPage;
	private List<Map<String, Object>> users;
	
	public UserBean() {
	}
	
	
	public UserBean(Integer currPage, Integer pageSize, Integer totalPage, List<Map<String, Object>> users) {
		this.currPage = currPage;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.users = users;
	}


	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<Map<String, Object>> getUsers() {
		return users;
	}
	public void setUsers(List<Map<String, Object>> users) {
		this.users = users;
	}
}
