package com.lrm.defacto.backend.priority;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrm.defacto.backend.priority.statusmessages.PriorityErrorMessages;
import com.lrm.defacto.backend.priority.statusmessages.PriorityErrorStatuses;
import com.lrm.defacto.backend.priority.statusmessages.PrioritySuccessMessages;
import com.lrm.defacto.backend.priority.statusmessages.PrioritySuccessStatuses;
import com.lrm.defacto.backend.util.Response;

@RestController
@RequestMapping("/priority")
public class PriorityCTRL {

	@Autowired
	private PrioritySERV priorityServ;

	@PostMapping("/list")
	public ResponseEntity<?> getPriorities() {
		try {
			List<PriorityMODL> priorityList = this.priorityServ.getPriorityList();
			return new ResponseEntity<>(new Response<>(PrioritySuccessStatuses.SUCCESS_FETCH_LIST,
					PrioritySuccessMessages.SUCCESS_FETCH_LIST, priorityList), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(new Response<>(PriorityErrorStatuses.ERROR_FETCH_LIST,
					PriorityErrorMessages.ERROR_FETCH_LIST, new HashMap<>()), HttpStatus.BAD_REQUEST);

		}
	}

}
