package com.cydeo.mapper;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // convert to Entity

    public Role convertToEntity(RoleDTO dto){

        return modelMapper.map(dto, Role.class);
    }


    // convert to DTO

    public RoleDTO convertToDto(Role role){

        return modelMapper.map(role,RoleDTO.class);
    }

}
