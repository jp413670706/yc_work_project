package com.yc.mybatis.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 数据分页处理的封装实体类
 * 
 * @author Administrator
 *
 */
public class BlogBean implements Serializable{	
	private static final long serialVersionUID = -8576336561135328077L;
	// 入参
	private int pagesize;
	private int pagenum;

	// 出参
	private List<Blog> blogs;
	private int totalSize;
	private int maxpage = 1;
	
	public BlogBean() {
	}

	public BlogBean(int pagesize, int pagenum) {
		this.pagesize = pagesize;
		this.pagenum = pagenum;
	}


	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		if(totalSize > 0){			
			maxpage = (totalSize / pagesize) + (totalSize % pagesize == 0 ? 0 : 1);
		}
	}

	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

	@Override
	public String toString() {
		return "入参==>pagesize=" + pagesize + ", pagenum=" + pagenum  + 
				"\n出参==> totalSize=" + totalSize + ", maxpage=" + maxpage + ", blogs=" + blogs;
	}

	
	
}
