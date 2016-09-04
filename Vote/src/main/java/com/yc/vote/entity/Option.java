package com.yc.vote.entity;

public class Option {
	private Integer voId;
	private String voOption;
	private Integer vsId;
	private Integer voOrder;
	private Integer voteCount;

	public Integer getVoId() {
		return voId;
	}

	public void setVoId(Integer voId) {
		this.voId = voId;
	}

	public String getVoOption() {
		return voOption;
	}

	public void setVoOption(String voOption) {
		this.voOption = voOption;
	}

	public Integer getVsId() {
		return vsId;
	}

	public void setVsId(Integer vsId) {
		this.vsId = vsId;
	}

	public Integer getVoOrder() {
		return voOrder;
	}

	public void setVoOrder(Integer voOrder) {
		this.voOrder = voOrder;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		return "Option [voId=" + voId + ", voOption=" + voOption + ", vsId=" + vsId + ", voOrder=" + voOrder + ", voteCount=" + voteCount + "]";
	}

}
