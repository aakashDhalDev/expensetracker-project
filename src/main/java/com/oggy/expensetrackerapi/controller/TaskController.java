package com.oggy.expensetrackerapi.controller;

import com.oggy.expensetrackerapi.entity.Task;
import com.oggy.expensetrackerapi.service.TaskService;
import com.oggy.expensetrackerapi.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
}
