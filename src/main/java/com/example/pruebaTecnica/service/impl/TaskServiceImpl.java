package com.example.pruebaTecnica.service.impl;

import com.example.pruebaTecnica.model.Task;
import com.example.pruebaTecnica.repository.ITaskRepository;
import com.example.pruebaTecnica.service.interfaces.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Service impl for Task
 *
 * @author Adriana Villamizar Vera
 * @since 1.0
 */

public class TaskServiceImpl implements ITaskService {


    Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private ITaskRepository taskRepository;


    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public Task createTask(Task aTask) {
        return this.taskRepository.save(aTask);
    }

    @Override
    public void deleteTask(Integer aId) {
        this.taskRepository.deleteById(aId);
    }

    @Override
    public void updateTask(Task aTask) {

    }

    @Autowired
    public void setTaskRepository(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
