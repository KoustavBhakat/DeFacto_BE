package com.lrm.defacto.backend.priority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrioritySERV {

	@Autowired
	private PriorityREPO priorityRepo;

	public List<PriorityMODL> getPriorityList() {
		List<PriorityMODL> priorities = this.priorityRepo.findAll();
		return priorities;
	}

	public PriorityMODL getPriorityById(String id) {
		return this.priorityRepo.findById(id).get();
	}

}
