package com.yc.vote.entity;

import org.hibernate.validator.constraints.Length;

public class User {
	private Integer vuId;
	@Length(max = 10, message = "长度最大为10...")
	private String vuUsername;
	private String vuPassword;
	private String vuStatus;
	private Integer vuVersion;
	private String confirmPassword;
	private String vuEmail;

	public User() {
	}

	public User(String vuUsername, String vuPassword) {
		this.vuUsername = vuUsername;
		this.vuPassword = vuPassword;
	}

	public Integer getVuId() {
		return vuId;
	}

	public void setVuId(Integer vuId) {
		this.vuId = vuId;
	}

	public String getVuUsername() {
		return vuUsername;
	}

	public void setVuUsername(String vuUsername) {
		this.vuUsername = vuUsername;
	}

	public String getVuPassword() {
		return vuPassword;
	}

	public void setVuPassword(String vuPassword) {
		this.vuPassword = vuPassword;
	}

	public String getVuStatus() {
		return vuStatus;
	}

	public void setVuStatus(String vuStatus) {
		this.vuStatus = vuStatus;
	}

	public Integer getVuVersion() {
		return vuVersion;
	}

	public void setVuVersion(Integer vuVersion) {
		this.vuVersion = vuVersion;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getVuEmail() {
		return vuEmail;
	}

	public void setVuEmail(String vuEmail) {
		this.vuEmail = vuEmail;
	}

	@Override
	public String toString() {
		return "User [vuId=" + vuId + ", vuUsername=" + vuUsername + ", vuPassword=" + vuPassword + ", vuStatus=" + vuStatus + ", vuVersion=" + vuVersion + ", confirmPassword=" + confirmPassword
				+ ", vuEmail=" + vuEmail + "]";
	}

}
