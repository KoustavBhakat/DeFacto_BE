package com.lrm.defacto.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserREPO extends JpaRepository<UserMODL, String> {

	public UserMODL findByFullName(String username);

	public UserMODL findByEmail(String email);

}
