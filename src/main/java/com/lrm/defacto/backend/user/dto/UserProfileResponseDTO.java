package com.lrm.defacto.backend.user.dto;

import java.util.List;

public class UserProfileResponseDTO {

	private String name;

	private String reportingTo;

	private String employeeId;

	private String pods;

	private String role;

	private List<String> skills;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getPods() {
		return pods;
	}

	public void setPods(String pods) {
		this.pods = pods;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

}
