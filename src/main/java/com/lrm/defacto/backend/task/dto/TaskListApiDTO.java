package com.lrm.defacto.backend.task.dto;

public class TaskListApiDTO {

	private String taskListType;

	private String userId;

	private Integer p;

	private Integer pp;

	private String query;

	public String getTaskListType() {
		return taskListType;
	}

	public void setTaskListType(String taskListType) {
		this.taskListType = taskListType;
	}

	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
	}

	public Integer getPp() {
		return pp;
	}

	public void setPp(Integer pp) {
		this.pp = pp;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
