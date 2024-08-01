package com.example.lab7.Controllers;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Models.Student;
import com.example.lab7.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {


    public final StudentService studentService;


    @GetMapping("/get")
    public ResponseEntity getAllStudents() {

        if (studentService.getStudents().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Student list is empty")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                studentService.getStudents()
        );

    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Student added successfully")
        );


    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        if (studentService.updateStudent(id, student)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Student updated successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Student not found")
            );
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {


        if (studentService.removeStudent(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Student deleted successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Student not found or empty")
            );
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity getStudentsByCity(@PathVariable String city) {
        if (studentService.getStudentsByCity(city) == null || studentService.getStudentsByCity(city).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("No students found in this city")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentsByCity(city));
    }

    @GetMapping("/age")
    public ResponseEntity getStudentsByAge(@RequestParam int minAge, @RequestParam int maxAge) {
        if (studentService.getStudentsByAge(minAge, maxAge) == null || studentService.getStudentsByAge(minAge, maxAge).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("No students found in this age range")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentsByAge(minAge, maxAge));

    }

    @GetMapping("/graduate/{status}")
    public ResponseEntity getStudentsByGraduateStatus(@PathVariable String status) {
        if (studentService.getStudentsByGraduateStatus(status) == null || studentService.getStudentsByGraduateStatus(status).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("No students found with this graduate status")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentsByGraduateStatus(status));
    }

    @GetMapping("/excellent")
    public ResponseEntity getExcellentStudents() {

        if (studentService.getExcellentStudents() == null || studentService.getExcellentStudents().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("No excellent students found")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getExcellentStudents());
    }

}
