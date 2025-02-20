package com.oggy.expensetrackerapi.service;

import com.oggy.expensetrackerapi.entity.Task;
import com.oggy.expensetrackerapi.repository.TaskRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRespository taskRepo;

    @Override
    public List<Task> getAllTasks(){
        return taskRepo.findAll();
    }
}
