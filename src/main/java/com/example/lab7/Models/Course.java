package com.example.lab7.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor

public class Course {

    @NotEmpty(message = "ID cannot be empty")
    private String id;

    @NotEmpty(message = "Course name cannot be empty")
    private String courseName;

    @NotEmpty(message = "Level cannot be empty")
    @Pattern(regexp = "^(advance|intermediate|beginner)$", message = "Level must be one of: advance, intermediate, beginner")
    private String level;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;


    private ArrayList<Student> studentsCourse;

}
