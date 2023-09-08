package com.lrm.defacto.backend.task.dto;

public class TaskUpdateDTO {

	private String taskId;

	private String status;

	private String utilizedHours;

	private String percentCompletion;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUtilizedHours() {
		return utilizedHours;
	}

	public void setUtilizedHours(String utilizedHours) {
		this.utilizedHours = utilizedHours;
	}

	public String getPercentCompletion() {
		return percentCompletion;
	}

	public void setPercentCompletion(String percentCompletion) {
		this.percentCompletion = percentCompletion;
	}

}
