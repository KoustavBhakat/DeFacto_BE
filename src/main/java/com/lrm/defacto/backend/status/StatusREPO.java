package com.lrm.defacto.backend.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusREPO extends JpaRepository<StatusMODL, String> {

	public StatusMODL findByCode(String code);

}
