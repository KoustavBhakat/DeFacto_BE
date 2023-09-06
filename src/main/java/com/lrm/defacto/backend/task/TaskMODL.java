package com.lrm.defacto.backend.task;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.lrm.defacto.backend.user.UserMODL;

@Entity
@Table(name = "task_details")
@EntityListeners(AuditingEntityListener.class)
public class TaskMODL {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	private String name;

	@Column(columnDefinition = "LONGTEXT")
	private String description;

	@Column(name = "parent_id")
	private String parentId;

	@Column(name = "task_creation_date")
	private Date taskCreationDate;

	@Column(name = "is_splitted")
	private Boolean isSplitted;

	@Column(name = "due_date")
	private Date taskDueDate;

	@ManyToOne
	@JoinColumn(name = "assigned_by")
	private UserMODL assignedBy;

	@ManyToOne
	@JoinColumn(name = "assigned_to")
	private UserMODL assignedTo;

	@Column(name = "project_id")
	private String projectId;

	private String priority;

	@Column(name = "expected_hours")
	private Integer expectedHours;

	private String status;

	@Column(name = "utilized_hours")
	private Integer utilizedHours;

	@Column(name = "completion_percentage")
	private String completionPercentage;

	@CreatedDate
	private Date createdAt;

	@LastModifiedDate
	private Date updatedAt;

	@Transient
	private List<TaskMODL> subTaskList;

	public List<TaskMODL> getSubTaskList() {
		return subTaskList;
	}

	public void setSubTaskList(List<TaskMODL> subTaskList) {
		this.subTaskList = subTaskList;
	}

	public Boolean getIsSplitted() {
		return isSplitted;
	}

	public void setIsSplitted(Boolean isSplitted) {
		this.isSplitted = isSplitted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTaskCreationDate() {
		return taskCreationDate;
	}

	public void setTaskCreationDate(Date taskCreationDate) {
		this.taskCreationDate = taskCreationDate;
	}

	public Date getTaskDueDate() {
		return taskDueDate;
	}

	public void setTaskDueDate(Date taskDueDate) {
		this.taskDueDate = taskDueDate;
	}

	public UserMODL getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(UserMODL assignedBy) {
		this.assignedBy = assignedBy;
	}

	public UserMODL getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(UserMODL assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Integer getExpectedHours() {
		return expectedHours;
	}

	public void setExpectedHours(Integer expectedHours) {
		this.expectedHours = expectedHours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUtilizedHours() {
		return utilizedHours;
	}

	public void setUtilizedHours(Integer utilizedHours) {
		this.utilizedHours = utilizedHours;
	}

	public String getCompletionPercentage() {
		return completionPercentage;
	}

	public void setCompletionPercentage(String completionPercentage) {
		this.completionPercentage = completionPercentage;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
