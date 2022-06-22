package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.*;


@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private String confirmPassWord;
    private boolean enabled;
    private String phone;
    private RoleDTO role;
    private Gender gender;
}
