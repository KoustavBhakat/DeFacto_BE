package com.lrm.defacto.backend.task;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lrm.defacto.backend.status.StatusMODL;
import com.lrm.defacto.backend.user.UserMODL;

@Repository
public interface TaskREPO extends PagingAndSortingRepository<TaskMODL, String> {

	public Page<TaskMODL> findAllByNameAndAssignedToAndStatus(String name, UserMODL user_id, String statusId,
			Pageable pageable);

	public Page<TaskMODL> findAllByAssignedToAndStatus(UserMODL user_id, String statusId, Pageable pageable);

	public List<TaskMODL> findByParentId(String taskId);

	public Integer countByAssignedToAndStatus(UserMODL userId, String status);

	public Integer countByAssignedBy(UserMODL userId);

	public Page<TaskMODL> findAllByAssignedBy(UserMODL currentUser, Pageable pageable);

	public Page<TaskMODL> findAllByNameAndAssignedBy(String query, UserMODL currentUser, Pageable pageable);

}
