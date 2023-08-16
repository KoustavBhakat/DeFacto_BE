package com.lrm.defacto.backend.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lrm.defacto.backend.core.auth.dto.JWTRequestBody;
import com.lrm.defacto.backend.core.auth.util.JwtUtil;
import com.lrm.defacto.backend.user.dto.UserDTO;

@Service
public class UserSERV implements UserDetailsService{
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		UserMODL account = this.getByUsername(username);
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
//		if (account != null) {
//			if (account.getUserTypeId().getCode() != null) {
//				roles.add(new SimpleGrantedAuthority("ROLE_" + account.getUserTypeId().getCode()));
//			}
//			return new User(account.getUserName(), account.getUserName(), roles);
//		}
		return null;
		
	}
	
	public Object login(UserDTO userDto) {
		
		String jwtToken = this.jwtUtil.generateJWTToken(new JWTRequestBody(userDto.getUsername(), new Date()));
		return jwtToken;
	}

}
