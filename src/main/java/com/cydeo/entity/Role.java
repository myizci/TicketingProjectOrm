package com.cydeo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter
@Table(name="roles")
@Entity
public class Role extends BaseEntity{

    private String description;

}
