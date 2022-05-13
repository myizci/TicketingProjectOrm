package com.cydeo.mapper;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class TaskMapper {
    ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper; // map method map(parameter, Target.class)
    }

    // dto to entity


    public Task convertToEntity(TaskDTO dto){
        return   modelMapper.map(dto,Task.class);
    }

    //entity to dto

    public TaskDTO convertToDto(Task entity){
        return modelMapper.map(entity,TaskDTO.class);
    }

}
