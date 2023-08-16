package com.lrm.defacto.backend.user;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lrm.defacto.backend.user.dto.UserDTO;

@RestController
public class UserCTRL {

	@Autowired
	private UserSERV userServ;

	@PostMapping("/login")
	public Object login(@RequestBody UserDTO userDto) {
		String token = (String)this.userServ.login(userDto);
		return new HashMap<String,String>(){{put("response",token);}};
	}
}
