package com.oggy.expensetrackerapi.repository;

import com.oggy.expensetrackerapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRespository extends JpaRepository<Task, Integer>{
}
