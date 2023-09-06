package com.lrm.defacto.backend.status;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrm.defacto.backend.status.StatusMessages.ErrorMessages;
import com.lrm.defacto.backend.status.StatusMessages.ErrorStatuses;
import com.lrm.defacto.backend.status.StatusMessages.SuccessMessages;
import com.lrm.defacto.backend.status.StatusMessages.SuccessStatuses;
import com.lrm.defacto.backend.util.Response;

@RequestMapping("/status")
@RestController
public class StatusCTRL {

	@Autowired
	private StatusSERV statusServ;

	@PostMapping("/list")
	public ResponseEntity<?> getStatusList() {

		try {
			List<StatusMODL> statusList = this.statusServ.getStatusList();
			return new ResponseEntity<>(new Response<>(SuccessStatuses.SUCCESS_STATUS_LIST,
					SuccessMessages.SUCCESS_STATUS_LIST, statusList), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new Response<>(ErrorStatuses.ERROR_STATUS_LIST, ErrorMessages.ERROR_STATUS_LIST, new HashMap<>()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
