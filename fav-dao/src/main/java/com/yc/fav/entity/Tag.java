package com.yc.fav.entity;

public class Tag {
	private int tid;
	private String tname;
	private int tcount;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getTcount() {
		return tcount;
	}
	public void setTcount(int tcount) {
		this.tcount = tcount;
	}
	@Override
	public String toString() {
		return "\nTag [tid=" + tid + ", tname=" + tname + ", tcount=" + tcount
				+ "]";
	}
}
