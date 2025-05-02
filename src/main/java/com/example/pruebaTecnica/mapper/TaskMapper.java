package com.example.pruebaTecnica.mapper;

import com.example.pruebaTecnica.dto.TaskDTO;
import com.example.pruebaTecnica.model.Task;
import com.example.pruebaTecnica.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public Task toTask(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDTO.getId() );
        task.setIdUser(taskDTO.getIdUser());
        task.setDate( taskDTO.getDate() );
        task.setRegisterDate( taskDTO.getRegisterDate() );
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setStatus( taskDTO.getStatus() );

        return task;
    }

    public TaskDTO toTaskDTO(Task tarea) {
        if ( tarea == null ) {
            return null;
        }
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId( tarea.getId() );
        taskDTO.setUserName( taskUserName(tarea) );
        taskDTO.setIdUser( tarea.getIdUser() );
        taskDTO.setName( tarea.getName());
        taskDTO.setDescription( tarea.getDescription() );
        taskDTO.setDate( tarea.getDate() );
        taskDTO.setRegisterDate( tarea.getRegisterDate() );
        taskDTO.setStatus( tarea.getStatus().toUpperCase());


        return taskDTO;
    }

    public List<TaskDTO> toTaskDTOList(List<Task> tareas) {
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for ( Task tarea : tareas ) {
            if ( tarea == null ) {
                return null;
            }
            TaskDTO taskDTO = new TaskDTO();

            taskDTO.setId( tarea.getId() );
            taskDTO.setUserName( taskUserName(tarea) );
            taskDTO.setIdUser( tarea.getIdUser() );
            taskDTO.setName( tarea.getName());
            taskDTO.setDescription( tarea.getDescription() );
            taskDTO.setDate( tarea.getDate() );
            taskDTO.setRegisterDate( tarea.getRegisterDate() );
            taskDTO.setStatus( tarea.getStatus());

            taskDTOList.add(taskDTO);
        }



        return taskDTOList;
    }

    private String taskUserName(Task tarea) {
        if ( tarea == null ) {
            return null;
        }
        User usuario = tarea.getUser();
        if ( usuario == null ) {
            return null;
        }
        String nombre = usuario.getName();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }
}
