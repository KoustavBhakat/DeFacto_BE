package com.lrm.defacto.backend.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user_details")
@EntityListeners(AuditingEntityListener.class)
public class UserMODL {

	@Id
	private String id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "pod_id")
	private String podId;

	private String email;

	@Column(name = "bandwidth", columnDefinition = "Integer default 100")
	private Integer bandwidth;

	@Column(name = "total_worked_hours")
	private Integer totalWorkedHours;

	private String status;

	@Column(name = "allocation_percent")
	private Integer allocationPercent;

	@Column(name = "created_at")
	@CreatedDate
	private Date createdAt;

	@Column(name = "updated_at")
	@LastModifiedDate
	private Date updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPodId() {
		return podId;
	}

	public void setPodId(String podId) {
		this.podId = podId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(Integer bandwidth) {
		this.bandwidth = bandwidth;
	}

	public Integer getTotalWorkedHours() {
		return totalWorkedHours;
	}

	public void setTotalWorkedHours(Integer totalWorkedHours) {
		this.totalWorkedHours = totalWorkedHours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getAllocationPercent() {
		return allocationPercent;
	}

	public void setAllocationPercent(Integer allocationPercent) {
		this.allocationPercent = allocationPercent;
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

}
