package com.lrm.defacto.backend.core.auth.dto;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = -4579741606523138076L;
	private final String jwt;
	private String userId;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public AuthenticationResponse(String jwt, String userId) {
		this.jwt = jwt;
		this.userId = userId;
	}

	public String getJwt() {
		return jwt;
	}
}
