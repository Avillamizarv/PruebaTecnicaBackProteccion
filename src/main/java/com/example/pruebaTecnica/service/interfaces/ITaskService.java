package com.example.pruebaTecnica.service.interfaces;

import com.example.pruebaTecnica.model.Task;
import com.example.pruebaTecnica.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Task
 *
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@Service
public interface ITaskService {

    List<Task> findAll();

    Task createTask(Task aTask);

    void deleteTask(Integer aId);

    void updateTask(Task aTask);
}
