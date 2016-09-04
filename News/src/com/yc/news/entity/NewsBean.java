package com.yc.news.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class NewsBean implements Serializable {
	private static final long serialVersionUID = 8314050786345782123L;
	private int currPage;
	private int totalPage;
	private List<Map<String, Object>> newses;

	public NewsBean() {
	}

	public NewsBean(int currPage, int totalPage, List<Map<String, Object>> newses) {
		super();
		this.currPage = currPage;
		this.totalPage = totalPage;
		this.newses = newses;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<Map<String, Object>> getNewses() {
		return newses;
	}

	public void setNewses(List<Map<String, Object>> newses) {
		this.newses = newses;
	}
}
