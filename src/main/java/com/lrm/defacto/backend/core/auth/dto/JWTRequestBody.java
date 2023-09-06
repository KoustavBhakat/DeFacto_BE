package com.lrm.defacto.backend.core.auth.dto;

import java.io.Serializable;
import java.util.Date;

public class JWTRequestBody implements Serializable {

	// private static final long serialVersionUID = 3287504140027037558L;
	private String username;
	private String emailDomain;

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public JWTRequestBody(String username) {
		this.username = username;
	}

	public JWTRequestBody(String username, String emailDomain) {
		this.username = username;
		this.emailDomain = emailDomain;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
