package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.ResponseWrapper;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllTasks() {

        List<TaskDTO> taskDTO = taskService.listAllTasks();
        return ResponseEntity.ok(new ResponseWrapper("All tasks successfully retrieved", taskDTO, HttpStatus.OK));
    }

    @GetMapping ("/{taskId}")
    public ResponseEntity<ResponseWrapper> getOneTask(@PathVariable("taskId") Long taskId) {

        TaskDTO taskDTO = taskService.findById(taskId);
        return ResponseEntity.ok(new ResponseWrapper("The task successfully retrieved", taskDTO, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> insertTask(@RequestBody TaskDTO taskDTO) {

        taskService.save(taskDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("One task successfully created",HttpStatus.CREATED));

    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateTask(@RequestBody TaskDTO taskDTO) {
        taskService.update(taskDTO);
        return ResponseEntity.ok(new ResponseWrapper("The task successfully updated", taskDTO, HttpStatus.OK));


    }

    @DeleteMapping ("/{taskId}")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable("taskId") Long taskId) {

        taskService.delete(taskId);
        return ResponseEntity.ok(new ResponseWrapper("One task successfully deleted", HttpStatus.OK));

    }




    @GetMapping("/employee/pending-tasks")
    public ResponseEntity<ResponseWrapper> employeePendingTasks() {

         List<TaskDTO> list = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);
        return ResponseEntity.ok(new ResponseWrapper("Non-complete tasks are listed",list, HttpStatus.OK));
    }

    @PutMapping("/employee/update")
    public ResponseEntity<ResponseWrapper> employeeUpdateTask(@RequestBody TaskDTO taskDTO) {

       taskService.updateStatus(taskDTO);
        return ResponseEntity.ok(new ResponseWrapper("Non-complete tasks are listed", HttpStatus.OK));

    }


    @GetMapping("/employee/archive")
    public ResponseEntity<ResponseWrapper> employeeArchivedTasks() {
        List<TaskDTO> list = taskService.listAllTasksByStatus(Status.COMPLETE);
        return ResponseEntity.ok(new ResponseWrapper("Complete tasks are listed",list, HttpStatus.OK));

    }

}
