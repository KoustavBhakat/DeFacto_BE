package com.lrm.defacto.backend.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusSERV {

	@Autowired
	private StatusREPO statusRepo;

	public List<StatusMODL> getStatusList() {
		return this.statusRepo.findAll();
	}
	
	public StatusMODL getStatusByCode(String code) {
		return this.statusRepo.findByCode(code);
	}

}
