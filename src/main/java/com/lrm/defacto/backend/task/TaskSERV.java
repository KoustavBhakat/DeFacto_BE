package com.lrm.defacto.backend.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lrm.defacto.backend.priority.PriorityMODL;
import com.lrm.defacto.backend.priority.PrioritySERV;
import com.lrm.defacto.backend.project.ProjectSERV;
import com.lrm.defacto.backend.project.dto.ProjectDTO;
import com.lrm.defacto.backend.status.StatusMODL;
import com.lrm.defacto.backend.status.StatusSERV;
import com.lrm.defacto.backend.task.dto.TaskCreateDTO;
import com.lrm.defacto.backend.task.dto.TaskDetailsDTO;
import com.lrm.defacto.backend.task.dto.TaskListApiDTO;
import com.lrm.defacto.backend.task.dto.TaskListType;
import com.lrm.defacto.backend.user.UserMODL;
import com.lrm.defacto.backend.user.UserSERV;

@Service
public class TaskSERV {

	@Autowired
	private ProjectSERV projectServ;

	@Autowired
	private TaskREPO taskRepo;

	@Autowired
	private StatusSERV statusServ;

	@Autowired
	private UserSERV userServ;

	@Autowired
	private PrioritySERV priorityServ;

	public TaskMODL addTask(TaskCreateDTO taskCreateDto) throws Exception {

		if (taskCreateDto.getName() == null) {
			throw new Exception("Invalid name");
		}
		if (taskCreateDto.getTaskDueDate() == null) {
			throw new Exception("Invalid end date");
		}

		if (taskCreateDto.getAssignedBy() == null) {
			throw new Exception("Invalid Assign By");
		}

		if (taskCreateDto.getAssignedTo() == null) {
			throw new Exception("Invalid Assignee");
		}
		if (taskCreateDto.getPriority() == null) {
			throw new Exception("Invalid priority");
		}

		ProjectDTO project = this.projectServ.getProjectById(taskCreateDto.getProjectId());
		PriorityMODL priority = this.priorityServ.getPriorityById(taskCreateDto.getPriority());
		UserMODL assignedBy = this.userServ.getUserById(taskCreateDto.getAssignedBy());
		UserMODL assignedTo = this.userServ.getUserById(taskCreateDto.getAssignedTo());

		TaskMODL taskModl = new TaskMODL();

		taskModl.setName(taskCreateDto.getName());
		taskModl.setDescription(taskCreateDto.getDescription());
		taskModl.setProjectId(project != null ? project.getParentTitle() : taskCreateDto.getProjectId());
		taskModl.setPriority(priority != null ? priority.getLabel() : taskCreateDto.getPriority());
		taskModl.setAssignedBy(assignedBy != null ? assignedBy : null);
		taskModl.setAssignedTo(assignedTo != null ? assignedTo : null);
		taskModl.setTaskDueDate(taskCreateDto.getTaskDueDate());
		taskModl.setExpectedHours(taskCreateDto.getExpectedHours());

		StatusMODL status = this.statusServ.getStatusByCode(TaskListType.NEW.name());
		taskModl.setIsSplitted(false);
		taskModl.setStatus(status.getName());
		taskModl.setTaskCreationDate(new Date());

		TaskMODL savedTask = this.taskRepo.save(taskModl);
		taskModl = null;
		taskCreateDto = null;
		project = null;
		priority = null;
		assignedBy = null;
		assignedTo = null;
		status = null;

		return savedTask;
	}

	public Page<?> getTaskList(TaskListApiDTO taskListApiDto) {

		Pageable pageable = null;

		if (taskListApiDto.getP() != null && taskListApiDto.getPp() != null) {
			pageable = PageRequest.of(taskListApiDto.getP(), taskListApiDto.getPp());
		} else {
			pageable = PageRequest.of(0, Integer.MAX_VALUE);
		}

		Page<TaskMODL> taskList = null;

		if (taskListApiDto.getTaskListType() != null) {

			if (taskListApiDto.getTaskListType().equalsIgnoreCase(TaskListType.NEW.name())) {

				StatusMODL status = this.statusServ.getStatusByCode(TaskListType.NEW.name());

				if (taskListApiDto.getQuery() == null) {
					taskList = this.taskRepo.findAllByAssignedToAndStatus(taskListApiDto.getUserId(), status.getId(),
							pageable);
				} else {
					taskList = this.taskRepo.findAllByNameAndAssignedToAndStatus(taskListApiDto.getQuery(),
							taskListApiDto.getUserId(), status.getId(), pageable);
				}

			} else if (taskListApiDto.getTaskListType().equalsIgnoreCase(TaskListType.IN_PROGRESS.name())) {

				StatusMODL status = this.statusServ.getStatusByCode(TaskListType.IN_PROGRESS.name().replace('_', ' '));

				if (taskListApiDto.getQuery() == null) {
					taskList = this.taskRepo.findAllByAssignedToAndStatus(taskListApiDto.getUserId(), status.getId(),
							pageable);
				} else {
					taskList = this.taskRepo.findAllByNameAndAssignedToAndStatus(taskListApiDto.getQuery(),
							taskListApiDto.getUserId(), status.getId(), pageable);
				}

			} else if (taskListApiDto.getTaskListType().equalsIgnoreCase(TaskListType.COMPLETE.name())) {

				StatusMODL status = this.statusServ.getStatusByCode(TaskListType.COMPLETE.name());

				if (taskListApiDto.getQuery() == null) {
					taskList = this.taskRepo.findAllByAssignedToAndStatus(taskListApiDto.getUserId(), status.getId(),
							pageable);
				} else {
					taskList = this.taskRepo.findAllByNameAndAssignedToAndStatus(taskListApiDto.getQuery(),
							taskListApiDto.getUserId(), status.getId(), pageable);
				}

			} else if (taskListApiDto.getTaskListType().equalsIgnoreCase(TaskListType.ASSIGNED_BY_ME.name())) {

				StatusMODL status = this.statusServ
						.getStatusByCode(TaskListType.ASSIGNED_BY_ME.name().replace('_', ' '));

				if (taskListApiDto.getQuery() == null) {
					taskList = this.taskRepo.findAllByAssignedToAndStatus(taskListApiDto.getUserId(), status.getId(),
							pageable);
				} else {
					taskList = this.taskRepo.findAllByNameAndAssignedToAndStatus(taskListApiDto.getQuery(),
							taskListApiDto.getUserId(), status.getId(), pageable);
				}
			}
		}

		return taskList;
	}

	public TaskMODL getDetailsOfATask(TaskDetailsDTO taskDetailsDto) throws Exception {

		if (taskDetailsDto.getTaskId() == null) {
			throw new Exception("Invalid Task Id");
		}

		Optional<TaskMODL> task = this.taskRepo.findById(taskDetailsDto.getTaskId());

		if (task.isEmpty()) {
			throw new Exception("Task Not Found");
		}

		TaskMODL retrievedTask = task.get();

		List<TaskMODL> subTasks = this.taskRepo.findByParentId(taskDetailsDto.getTaskId());

		if (subTasks != null) {

			Integer totalUtilizedHours = 0;

			for (TaskMODL subTask : subTasks) {
				totalUtilizedHours += subTask.getUtilizedHours();
			}

			retrievedTask.setUtilizedHours(totalUtilizedHours);
		}

		retrievedTask.setSubTaskList(subTasks);

		return retrievedTask;
	}

	public HashMap<String, Integer> getTotalTasksCount(TaskListApiDTO taskListApiDto) throws Exception {

		if (taskListApiDto.getUserId() == null) {
			throw new Exception("Invalid user id");
		}

		Integer newCount = this.taskRepo.countByAssignedToAndStatus(taskListApiDto.getUserId(),
				TaskListType.NEW.name());
		Integer inProgressCount = this.taskRepo.countByAssignedToAndStatus(taskListApiDto.getUserId(),
				TaskListType.IN_PROGRESS.name().replace('_', ' '));
		Integer completedCount = this.taskRepo.countByAssignedToAndStatus(taskListApiDto.getUserId(),
				TaskListType.COMPLETE.name());
		Integer assignedByMeCount = this.taskRepo.countByAssignedBy(taskListApiDto.getUserId());

		return new HashMap<String, Integer>() {
			{
				put("New", newCount);
				put("In Progress", inProgressCount);
				put("Complete", completedCount);
				put("Assigned By Me", assignedByMeCount);
			}
		};
	}

}
