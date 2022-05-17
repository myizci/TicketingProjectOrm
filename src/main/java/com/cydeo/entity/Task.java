package com.cydeo.entity;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Where(clause = "is_deleted=false")
@Table(name = "tasks")
public class Task extends BaseEntity{

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedEmployee;

    @NotBlank
    private String taskSubject;

    @NotBlank
    private String taskDetail;

    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    @Column(columnDefinition = "DATE")
    private LocalDate assignedDate;
}
