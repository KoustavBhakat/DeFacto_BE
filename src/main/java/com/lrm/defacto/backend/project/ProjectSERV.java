package com.lrm.defacto.backend.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lrm.defacto.backend.project.dto.ProjectDTO;

@Service
public class ProjectSERV {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${X-AUTH-USER}")
	private String userAuth;

	@Value("${X-AUTH-TOKEN}")
	private String userToken;

	@Value("${project.endpoint}")
	private String projectEndpoint;

	public Object getAllProjects() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-AUTH-USER", userAuth);
		headers.set("X-AUTH-TOKEN", userToken);

		HttpEntity<String> finalRequest = new HttpEntity<String>(headers);

		ResponseEntity<ArrayList> response = this.restTemplate.exchange(this.projectEndpoint, HttpMethod.GET,
				finalRequest, ArrayList.class);

		return response.getBody();
	}

	public ProjectDTO getProjectById(String id) {

		String endpoint = this.projectEndpoint + "/" + id;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-AUTH-USER", userAuth);
		headers.set("X-AUTH-TOKEN", userToken);

		HttpEntity<String> finalRequest = new HttpEntity<String>(headers);

		ResponseEntity<ProjectDTO> response = this.restTemplate.exchange(endpoint, HttpMethod.GET, finalRequest,
				ProjectDTO.class);

		return response.getBody();

	}
}
