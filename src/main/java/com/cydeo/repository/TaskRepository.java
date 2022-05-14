package com.cydeo.repository;

import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT COUNT (t) from Task t WHERE t.project.projectCode=?1 AND t.taskStatus<>'COMPLETE' ")
    int totalNonCompletedTasks(String projectCode);

  //  @Query("SELECT COUNT (t) from Task t WHERE t.project.projectStatus=?1 ANd t.taskStatus='Completed'")
    @Query(value = "SELECT COUNT(*)"+
    "FROM tasks t JOIN projects p ON t.project_id=p.id " +
    "WHERE p.project_code=?1 AND t.task_status='COMPLETED'",nativeQuery = true)
    int totalCompletedTasks(String projectCode);

    List<Task> findAllByProject(Project project);
}
