package com.yc.fav.entity;

public class Favorite {
	private int fid;
	private String flabel;
	private String furl;
	private String fdesc;
	private String ftags;

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFlabel() {
		return flabel;
	}

	public void setFlabel(String flabel) {
		this.flabel = flabel;
	}

	public String getFtags() {
		return ftags;
	}

	public void setFtags(String ftags) {
		this.ftags = ftags;
	}

	public String getFurl() {
		return furl;
	}

	public void setFurl(String furl) {
		this.furl = furl;
	}

	public String getFdesc() {
		return fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}

	@Override
	public String toString() {
		return "Favorite [fid=" + fid + ", flabel=" + flabel + ", furl=" + furl
				+ ", fdesc=" + fdesc + ", ftags=" + ftags + "]";
	}

}
