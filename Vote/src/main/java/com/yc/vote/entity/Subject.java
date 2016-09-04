package com.yc.vote.entity;

public class Subject {
	private Integer vsId;
	private String vsTitle;
	private String vsType;
	private Integer voteCount; // 投票的人数
	private Integer optionCount; // 主题的选项数

	public Integer getVsId() {
		return vsId;
	}

	public void setVsId(Integer vsId) {
		this.vsId = vsId;
	}

	public String getVsTitle() {
		return vsTitle;
	}

	public void setVsTitle(String vsTitle) {
		this.vsTitle = vsTitle;
	}

	public String getVsType() {
		return vsType;
	}

	public void setVsType(String vsType) {
		this.vsType = vsType;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public Integer getOptionCount() {
		return optionCount;
	}

	public void setOptionCount(Integer optionCount) {
		this.optionCount = optionCount;
	}

	@Override
	public String toString() {
		return "\nSubject [vsId=" + vsId + ", vsTitle=" + vsTitle + ", vsType=" + vsType + ", voteCount=" + voteCount + ", optionCount=" + optionCount + "]";
	}

}
