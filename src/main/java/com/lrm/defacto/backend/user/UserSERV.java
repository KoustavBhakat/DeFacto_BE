package com.lrm.defacto.backend.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.lrm.defacto.backend.core.auth.dto.JWTRequestBody;
import com.lrm.defacto.backend.core.auth.util.JwtUtil;
import com.lrm.defacto.backend.task.dto.TaskListApiDTO;
import com.lrm.defacto.backend.user.dto.UserDTO;

@Service
public class UserSERV implements UserDetailsService {

	@Autowired
	private JwtUtil jwtUtil;

	@Value("${get-all-users}")
	private String getUserEndpoint;

	@Value("${X-AUTH-USER}")
	private String userAuth;

	@Value("${X-AUTH-TOKEN}")
	private String userToken;

	@Autowired
	private UserREPO userRepo;

	@Autowired
	private RestTemplate restTemplate;

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
		return new User(username, username, roles);

	}

//	public Object login(UserDTO userDto) {
//
//		String jwtToken = this.jwtUtil.generateJWTToken(new JWTRequestBody(userDto.getUsername(), new Date()));
//		return jwtToken;
//	}

	public Object getAllUsers() throws RestClientException, URISyntaxException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-AUTH-USER", userAuth);
		headers.set("X-AUTH-TOKEN", userToken);

		HttpEntity<String> finalRequest = new HttpEntity<String>(headers);

		ResponseEntity<ArrayList> response = this.restTemplate.exchange(this.getUserEndpoint, HttpMethod.GET,
				finalRequest, ArrayList.class);

		return response.getBody();
	}

	public UserMODL getUserById(String id) {
		return this.userRepo.findById(id).get();
	}

	public Object getUserProfile(TaskListApiDTO taskListApiDto) {

		
		
		
		return null;
	}

}
