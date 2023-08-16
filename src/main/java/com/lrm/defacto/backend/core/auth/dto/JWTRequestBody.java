package com.lrm.defacto.backend.core.auth.dto;

import java.io.Serializable;
import java.util.Date;

public class JWTRequestBody implements Serializable {

	// private static final long serialVersionUID = 3287504140027037558L;
	private String username;
	private Date currentDate;

	public JWTRequestBody(String username) {
		this.username = username;
	}

	public JWTRequestBody(String username, Date currentDate) {
		this.username = username;
		this.currentDate = currentDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

}
