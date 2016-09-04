package com.yc.myspring.entity;

import java.util.List;

public class BeanMapping {
	private String id;
	private String className;
	private List<BeanProperty> beanPeoperties;

	public BeanMapping() {
	}

	public BeanMapping(String id, String className, List<BeanProperty> beanPeoperties) {
		super();
		this.id = id;
		this.className = className;
		this.beanPeoperties = beanPeoperties;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<BeanProperty> getBeanPeoperties() {
		return beanPeoperties;
	}

	public void setBeanPeoperties(List<BeanProperty> beanPeoperties) {
		this.beanPeoperties = beanPeoperties;
	}

	@Override
	public String toString() {
		return "\nBeanMapping [id=" + id + ", className=" + className + ", beanPeoperties=" + beanPeoperties + "]";
	}

}
