package com.lrm.defacto.backend.project;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrm.defacto.backend.task.statusmessages.TaskErrorMessages;
import com.lrm.defacto.backend.task.statusmessages.TaskErrorStatuses;
import com.lrm.defacto.backend.task.statusmessages.TaskSuccessMessages;
import com.lrm.defacto.backend.task.statusmessages.TaskSuccessStatuses;
import com.lrm.defacto.backend.util.Response;

@RequestMapping("/project")
@RestController
public class ProjectCTRL {

	@Autowired
	private ProjectSERV projectServ;

	@PostMapping("/list")
	public ResponseEntity<?> getProjectList() {

		try {
			return new ResponseEntity<>(new Response<>(TaskSuccessStatuses.SUCCESS_TASK_LIST,
					TaskSuccessMessages.SUCCESS_TASK_LIST, this.projectServ.getAllProjects()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new Response<>(TaskErrorStatuses.ERROR_ADD_TASK, TaskErrorMessages.ERROR_ADD_TASK, new HashMap<>()),
					HttpStatus.BAD_REQUEST);
		}

	}
}
