package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository; // pull data from DB
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        // 1- find all tasks in DB
        //2-use mapper
        // return a list of taskDTO

        List<Task> list = taskRepository.findAll();

      return   list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }


    @Override
    public void save(TaskDTO dto) {

        /*
        1) map to entity
        2) save
         */

        dto.setAssignedDate(LocalDate.now());
        dto.setTaskStatus(Status.OPEN);
        Task task = taskMapper.convertToEntity(dto);
        taskRepository.save(task);

    }

    @Override
    public void deleteById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        // task is an Optional object -> task.get() is a Task object

        if(task.isPresent()){
            task.get().setIsDeleted(true);
            taskRepository.save(task.get());
        }


    }

    @Override
    public TaskDTO findById(Long id) {
        //1) Go to DB and find by id
        //2) map it to TaskDTO
        //3) return

        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent()){
        return  taskMapper.convertToDTO(task.get());
        }

        return null;
    }

    @Override
    public void update(TaskDTO dto) {

        //1) go to DB and pull the corresponding entity

        Optional<Task> task = taskRepository.findById(dto.getId());

        // 2) we converted the information in the form (dto) into a Task entity;
        Task convertedTask = taskMapper.convertToEntity(dto);
        // what is convertedTask status?
        // what is convertedTask assigned date?
        // what is convertedTask id?

        if(task.isPresent()){
            convertedTask.setId(task.get().getId());
            convertedTask.setTaskStatus(task.get().getTaskStatus());
            convertedTask.setAssignedDate(task.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }

    }

    @Override
    public List<TaskDTO> findAllTasksByStatusIsNot(Status status) {
        /*
        Go to DB and find all tasks belonging to John,  hard code to a real employee
        Filter tasks by status
        Convert to DTO
        return
         */

        User loggedUser = userRepository.findByUserName("john@employee.com");

        List<Task> list =taskRepository.findAllByAssignedEmployeeAndTaskStatusIsNot(loggedUser,status);

       return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());


    }


}
