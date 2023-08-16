package com.lrm.defacto.backend.core.auth.config;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthoritiesImpl implements GrantedAuthority {

	private String authority;

	public GrantedAuthoritiesImpl(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
}
