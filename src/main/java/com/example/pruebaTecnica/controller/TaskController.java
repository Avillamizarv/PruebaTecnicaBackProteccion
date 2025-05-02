package com.example.pruebaTecnica.controller;

import com.example.pruebaTecnica.dto.TaskDTO;
import com.example.pruebaTecnica.service.interfaces.ITaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Task
 *
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private ITaskService iTaskService;

    @GetMapping ("/get")
    public ResponseEntity<List<TaskDTO>> getList(){

        try {
            List<TaskDTO> taskList = this.iTaskService.findAll();
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        }
        catch (Exception exception) {
            logger.error(String.valueOf(exception));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> crear(@RequestBody @Valid TaskDTO tareaDTO){

        try {
            TaskDTO tareaSaved = this.iTaskService.createTask(tareaDTO);
            return new ResponseEntity<>(tareaSaved, HttpStatus.OK);
        }
        catch (Exception exception) {
            logger.error(String.valueOf(exception));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<TaskDTO> actualizar(@RequestBody @Valid TaskDTO tareaDTO){

        try {
            TaskDTO tareaSaved = this.iTaskService.updateTask(tareaDTO);
            return new ResponseEntity<>(tareaSaved, HttpStatus.OK);
        }
        catch (Exception exception) {
            logger.error(String.valueOf(exception));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/changeStatus")
    public ResponseEntity<TaskDTO> cambiarEstado(@RequestParam Integer idTask, @RequestParam String status){

        try {
            TaskDTO tareaDTO = this.iTaskService.changeStatus(idTask, status);
            if (tareaDTO == null){
                /*logger.info("La tarea con id " + idTarea + " no se encuentra en la BD");*/
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(tareaDTO, HttpStatus.OK);
            }


        }
        catch (Exception exception) {
            logger.error(String.valueOf(exception));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable (value = "id") Integer idTask){

        try {
            this.iTaskService.deleteTask(idTask);
           return new ResponseEntity<>(null, HttpStatus.OK);

        }
        catch (Exception exception) {
            logger.error(String.valueOf(exception));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
