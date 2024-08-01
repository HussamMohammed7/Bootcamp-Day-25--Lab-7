package com.example.lab7.Models;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Student {
    @NotEmpty(message = "ID cannot be empty")
    private String id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Positive(message = "Age must be a positive number")
    private int age;
    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone number must start with 05 and be exactly 10 digits long")
    private String phone;

    @NotEmpty(message = "Graduate status cannot be empty")
    @Pattern(regexp = "^(graduate|not graduate)$", message = "Graduate status must be either 'graduate' or 'not graduate'")
    private String graduate;

    @Min(value = 0, message = "GPA must be at least 0.0")
    @Max(value = 5, message = "GPA must be at most 5.0")
    @Positive
    private double gpa;
}
