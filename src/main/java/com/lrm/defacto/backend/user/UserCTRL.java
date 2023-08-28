package com.lrm.defacto.backend.user;

import java.net.URISyntaxException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.lrm.defacto.backend.user.dto.UserDTO;

@RestController
@RequestMapping("/user")
public class UserCTRL {

	@Autowired
	private UserSERV userServ;

	@PostMapping("/login")
	public Object login(@RequestBody UserDTO userDto) {
		String token = (String) this.userServ.login(userDto);
		return new HashMap<String, String>() {
			{
				put("response", token);
			}
		};
	}

	@PostMapping("/list")
	public Object login() throws RestClientException, URISyntaxException {
		return this.userServ.getAllUsers();
	}
}
