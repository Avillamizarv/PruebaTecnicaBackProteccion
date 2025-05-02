package com.example.pruebaTecnica.service.impl;

import com.example.pruebaTecnica.dto.TaskDTO;
import com.example.pruebaTecnica.exception.ResourceNotFoundException;
import com.example.pruebaTecnica.mapper.TaskMapper;
import com.example.pruebaTecnica.model.Task;
import com.example.pruebaTecnica.model.User;
import com.example.pruebaTecnica.repository.ITaskRepository;
import com.example.pruebaTecnica.service.interfaces.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Stream;

/**
 * Service impl for Task
 *
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@Service
public class TaskServiceImpl implements ITaskService {


    Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private ITaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;


    @Override
    public List<TaskDTO> findAll() {
        List<Task> tasks = taskRepository.findAll();

        return this.taskMapper.toTaskDTOList(tasks);
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        taskDTO.setId(null);
        Task tarea = this.taskMapper.toTask(taskDTO);
        Task tareaCreada = this.taskRepository.save(tarea);


        TaskDTO tareaDTOResponse = this.taskMapper.toTaskDTO(tareaCreada);


        return tareaDTOResponse;
    }

    @Override
    public TaskDTO changeStatus(Integer id, String status) {
        Task tarea = this.taskRepository.findById(id).orElse(null);
        if (tarea == null){
            logger.info("La tarea con id " + id + " no se encuentra en la BD");
            return null;
        }
        if (tarea.getStatus().equals("FINALIZADA")){
            logger.info("La tarea con id " + id + " está FINALIZADA");
            return this.taskMapper.toTaskDTO(tarea);
        }

        tarea.setStatus(status.toUpperCase());
        Task tareaSaved = this.taskRepository.save(tarea);
        return this.taskMapper.toTaskDTO(tareaSaved);
    }

    @Override
    public void deleteTask(Integer aId) {
        this.taskRepository.deleteById(aId);
    }

    @Override
    public TaskDTO updateTask(TaskDTO aTaskDTO) {
        Task aTask = this.taskMapper.toTask(aTaskDTO);
        var taskBD = this.taskRepository.findById(aTask.getId()).orElseThrow(() -> new ResourceNotFoundException("No existe esta tarea" ));
        this.copiarPropiedadesObjetoAHaciaBIgnorandoNulosDeA(aTask, taskBD);
        taskBD.setName(aTask.getName());
        taskBD.setDescription(aTask.getDescription());
        taskBD.setDate(aTask.getDate());
        taskBD.setRegisterDate(aTask.getRegisterDate());
        taskBD.setStatus(aTask.getStatus());
        taskBD.setIdUser(aTask.getIdUser());
        aTask = taskBD;
        this.taskRepository.save(aTask);

        return aTaskDTO;
    }

    /**
     * One by one, the properties from the source to the destination are set, ignoring the fields null
     *
     * @param aOrigenA
     * @param aDestinoB
     * @return aDestinoB
     */
    public static Object copiarPropiedadesObjetoAHaciaBIgnorandoNulosDeA(Object aOrigenA, Object aDestinoB) {
        String[] ignoredAttributes = getAttributesNull(aOrigenA);
        BeanUtils.copyProperties(aOrigenA, aDestinoB, ignoredAttributes);
        return aDestinoB;
    }

    /**
     * Gets the null fields of the object to be analyzed
     *
     * @param object
     * @return wrappedSource
     */
    private static String[] getAttributesNull(Object object) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(object);
        return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
    }

    @Autowired
    public void setTaskRepository(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
