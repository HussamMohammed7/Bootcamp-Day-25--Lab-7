package com.example.lab7.Models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;


@Data
@AllArgsConstructor
public class School {


    @NotEmpty(message = "ID cannot be empty")
    private String id;

    @NotEmpty(message = "School name cannot be empty")
    private String schoolName;

    @NotEmpty(message = "School address cannot be empty")
    private String schoolAddress;


    @Pattern(regexp = "^(private|public)$", message = "Type must be either 'private' or 'public'")
    private String type; // Only "private" or "public"

    private boolean isOnlineSchool; // Indicates if the school is online or not

    @Positive(message = "Number of students must be a positive integer")
    private int numberOfStudents; // Field for the number of students
    private ArrayList<Course> courses;

}
