package com.lrm.defacto.backend.user;

import java.net.URISyntaxException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.lrm.defacto.backend.task.dto.TaskListApiDTO;
import com.lrm.defacto.backend.task.statusmessages.TaskErrorMessages;
import com.lrm.defacto.backend.task.statusmessages.TaskErrorStatuses;
import com.lrm.defacto.backend.task.statusmessages.TaskSuccessMessages;
import com.lrm.defacto.backend.task.statusmessages.TaskSuccessStatuses;
import com.lrm.defacto.backend.user.dto.UserDTO;
import com.lrm.defacto.backend.util.Response;

@RestController
@RequestMapping("/user")
public class UserCTRL {

	@Autowired
	private UserSERV userServ;

//	@PostMapping("/login")
//	public Object login(@RequestBody UserDTO userDto) {
//		String token = (String) this.userServ.login(userDto);
//		return new HashMap<String, String>() {
//			{
//				put("response", token);
//			}
//		};
//	}

	@PostMapping("/list")
	public Object getUserList() throws RestClientException, URISyntaxException {

		try {
			return new ResponseEntity<>(new Response<>(TaskSuccessStatuses.SUCCESS_TASK_LIST,
					TaskSuccessMessages.SUCCESS_TASK_LIST, this.userServ.getAllUsers()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new Response<>(TaskErrorStatuses.ERROR_ADD_TASK, TaskErrorMessages.ERROR_ADD_TASK, new HashMap<>()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/profile")
	public Object getProfileDetails(@RequestBody TaskListApiDTO taskListApiDto){

		try {
			return new ResponseEntity<>(new Response<>(TaskSuccessStatuses.SUCCESS_TASK_LIST,
					TaskSuccessMessages.SUCCESS_TASK_LIST, this.userServ.getUserProfile(taskListApiDto)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new Response<>(TaskErrorStatuses.ERROR_ADD_TASK, TaskErrorMessages.ERROR_ADD_TASK, new HashMap<>()),
					HttpStatus.BAD_REQUEST);
		}
	}
}
