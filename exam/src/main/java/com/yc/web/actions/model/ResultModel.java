package com.yc.web.actions.model;

public class ResultModel {
	private Integer times;
	private Object obj;
	
	
	
	
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	@Override
	public String toString() {
		return "ResultModel [times=" + times + ", obj=" + obj + "]";
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
