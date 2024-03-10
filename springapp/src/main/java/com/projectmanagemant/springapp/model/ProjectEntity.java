package com.projectmanagemant.springapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    @NotBlank(message = "Name is mandatory")
    private String name;

    @JsonFormat(pattern = "d-M-yyyy")  // Adjust the date format pattern
    private LocalDate startDate;
    @JsonFormat(pattern = "d-M-yyyy")  // Adjust the date format pattern
    private LocalDate endDate;

    @NotBlank(message = "Developer name should be included")
    private String developerName;
    @NotNull(message = "Required")
    private String category;
    private String description;

    @NotNull(message = "Required")
    private String status;
    @NotNull(message = "Required")
    private String link;
}
