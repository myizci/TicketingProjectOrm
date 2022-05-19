package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;

import java.util.List;

public interface TaskService {


    List<TaskDTO> listAllTasks();

    void save(TaskDTO dto);


    void deleteById(Long id);

    TaskDTO findById(Long id);

    void update(TaskDTO dto);


    List<TaskDTO> findAllTasksByStatusIsNot(Status status);

    void updateStatus(TaskDTO dto);

    List<TaskDTO> findAllTasksByStatus(Status status);

    int totalCompleteTask(String projectCode);

    int totalUncompletedTask(String projectCode);
}
