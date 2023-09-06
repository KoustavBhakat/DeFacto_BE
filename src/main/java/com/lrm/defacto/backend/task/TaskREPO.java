package com.lrm.defacto.backend.task;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskREPO extends PagingAndSortingRepository<TaskMODL, String> {

	public Page<TaskMODL> findAllByNameAndAssignedToAndStatus(String name, String user_id, String statusId,
			Pageable pageable);

	public Page<TaskMODL> findAllByAssignedToAndStatus(String user_id, String statusId, Pageable pageable);

	public List<TaskMODL> findByParentId(String taskId);

	public Integer countByAssignedToAndStatus(String userId, String status);

	public Integer countByAssignedBy(String userId);

}
