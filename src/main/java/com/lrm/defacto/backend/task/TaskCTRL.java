package com.lrm.defacto.backend.task;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrm.defacto.backend.task.dto.TaskCreateDTO;
import com.lrm.defacto.backend.task.dto.TaskDetailsDTO;
import com.lrm.defacto.backend.task.dto.TaskListApiDTO;
import com.lrm.defacto.backend.task.dto.TaskUpdateDTO;
import com.lrm.defacto.backend.task.statusmessages.TaskErrorMessages;
import com.lrm.defacto.backend.task.statusmessages.TaskErrorStatuses;
import com.lrm.defacto.backend.task.statusmessages.TaskSuccessMessages;
import com.lrm.defacto.backend.task.statusmessages.TaskSuccessStatuses;
import com.lrm.defacto.backend.util.Response;

@RestController
@RequestMapping("/task")
public class TaskCTRL<T> {

	@Autowired
	private TaskSERV taskServ;

	private final T hm = (T) new HashMap<>();

	@PostMapping("/create")
	public ResponseEntity<?> saveTask(@RequestBody TaskCreateDTO taskCreateDto) {
		try {
			System.out.println(hm.hashCode());
			TaskMODL task = this.taskServ.addTask(taskCreateDto);
			return new ResponseEntity<>(
					new Response<>(TaskSuccessStatuses.SUCCESS_TASK_ADD, TaskSuccessMessages.SUCCESS_TASK_ADD, task),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new Response<>(TaskErrorStatuses.ERROR_ADD_TASK, TaskErrorMessages.ERROR_ADD_TASK, hm),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/list")
	public ResponseEntity<?> taskList(@RequestBody TaskListApiDTO taskListApiDto) {
		try {
			System.out.println(hm.hashCode());
			Page<?> task = this.taskServ.getTaskList(taskListApiDto);
			return new ResponseEntity<>(
					new Response<>(TaskSuccessStatuses.SUCCESS_TASK_LIST, TaskSuccessMessages.SUCCESS_TASK_LIST, task),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new Response<>(TaskErrorStatuses.ERROR_ADD_TASK, TaskErrorMessages.ERROR_ADD_TASK, hm),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/details")
	public ResponseEntity<?> getTaskDetails(@RequestBody TaskDetailsDTO taskDetailsDto) {
		try {
			System.out.println(hm.hashCode());
			TaskMODL task = this.taskServ.getDetailsOfATask(taskDetailsDto);
			return new ResponseEntity<>(
					new Response<>(TaskSuccessStatuses.SUCCESS_TASK_ADD, TaskSuccessMessages.SUCCESS_TASK_ADD, task),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new Response<>(TaskErrorStatuses.ERROR_ADD_TASK, TaskErrorMessages.ERROR_ADD_TASK, hm),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/count")
	public ResponseEntity<?> getTaskCounts(@RequestBody TaskListApiDTO taskListApiDto) {
		try {
			System.out.println(hm.hashCode());
			HashMap<String, Integer> taskCount = this.taskServ.getTotalTasksCount(taskListApiDto);
			return new ResponseEntity<>(new Response<>(TaskSuccessStatuses.SUCCESS_TASK_ADD,
					TaskSuccessMessages.SUCCESS_TASK_ADD, taskCount), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new Response<>(TaskErrorStatuses.ERROR_ADD_TASK, TaskErrorMessages.ERROR_ADD_TASK, hm),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateTask(@RequestBody TaskUpdateDTO taskUpdateDto) {
		try {
			System.out.println(hm.hashCode());
			TaskMODL updatedTask = this.taskServ.updateTaskDeatils(taskUpdateDto);
			return new ResponseEntity<>(new Response<>(TaskSuccessStatuses.SUCCESS_TASK_ADD,
					TaskSuccessMessages.SUCCESS_TASK_ADD, updatedTask), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new Response<>(TaskErrorStatuses.ERROR_ADD_TASK, TaskErrorMessages.ERROR_ADD_TASK, hm),
					HttpStatus.BAD_REQUEST);
		}
	}

}
