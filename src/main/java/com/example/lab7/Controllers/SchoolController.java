package com.example.lab7.Controllers;

import lombok.RequiredArgsConstructor;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Models.School;
import com.example.lab7.Service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor

public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping("/get")
    public ResponseEntity getAllSchools() {
        ArrayList<School> schools = schoolService.getSchools();
        if (schools.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("School list is empty")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(schools);
    }

    @PostMapping("/add")
    public ResponseEntity addSchool(@Valid @RequestBody School school, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        schoolService.addSchool(school);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("School added successfully")
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateSchool(@PathVariable String id, @Valid @RequestBody School school, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        if (schoolService.updateSchool(id, school)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("School updated successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("School not found")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSchool(@PathVariable String id) {
        if (schoolService.removeSchool(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("School deleted successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("School not found or empty")
            );
        }
    }
    @GetMapping("/get-student-count")
    public ResponseEntity getNumberOfStudentsBySchoolId(@RequestParam("schoolId") String schoolId) {
        int studentCount = schoolService.getNumberOfStudentsBySchoolId(schoolId);
        if (studentCount == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("School not found")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Number of students : "+ studentCount)
        );
    }

    @GetMapping("/get-by-online-status")
    public ResponseEntity getSchoolsByOnlineStatus(@RequestParam("isOnline") boolean isOnline) {


        if (schoolService.getSchoolsByOnlineStatus(isOnline) == null || schoolService.getSchoolsByOnlineStatus(isOnline).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("No schools found with the specified online status")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(schoolService.getSchoolsByOnlineStatus(isOnline));
    }

    @PutMapping("/update-online-status")
    public ResponseEntity updateSchoolOnlineStatus(@RequestParam("schoolId") String schoolId, @RequestParam("isOnline") boolean isOnline) {

        boolean updated = schoolService.updateSchoolOnlineStatus(schoolId, isOnline);

        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("School online status updated successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("School with the specified ID not found")
            );
        }
    }
    @GetMapping("/get-by-type")
    public ResponseEntity getSchoolsByType(@RequestParam("type") String type) {

        if (        schoolService.getSchoolsByType(type)== null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("No schools found with the specified type")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(        schoolService.getSchoolsByType(type));
    }



}
