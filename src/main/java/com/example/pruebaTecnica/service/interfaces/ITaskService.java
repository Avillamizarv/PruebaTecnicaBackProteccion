package com.example.pruebaTecnica.service.interfaces;

import com.example.pruebaTecnica.dto.TaskDTO;
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
public interface ITaskService {

    List<TaskDTO> findAll();

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO updateTask(TaskDTO taskDTO);

    TaskDTO changeStatus(Integer id, String status);

    void deleteTask(Integer aId);

}
