package com.example.lab7.Controllers;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Models.Course;
import com.example.lab7.Models.Student;
import com.example.lab7.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getAllCourses() {
        ArrayList<Course> courses = courseService.getCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Course list is empty")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Course added successfully")
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id, @Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        if (courseService.updateCourse(id, course)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Course updated successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Course not found")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id) {
        if (courseService.removeCourse(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Course deleted successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Course not found or empty")
            );
        }
    }

    @PostMapping("/addStudent/{courseId}")
    public ResponseEntity addStudentToCourse(@PathVariable String courseId, @Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        if (courseService.addStudentToCourse(courseId, student)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Student added to course successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Failed to add student to course")
            );
        }
    }

    @DeleteMapping("/removeStudent/{courseId}/{studentId}")
    public ResponseEntity removeStudentFromCourse(@PathVariable String courseId, @PathVariable String studentId) {
        if (courseService.removeStudentFromCourse(courseId, studentId)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Student removed from course successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Failed to remove student from course")
            );
        }
    }

    @GetMapping("/level")
    public ResponseEntity getCoursesByLevel(@RequestParam String level) {

        if (courseService.getCoursesByLevel(level).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("No courses found with the level: " + level)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                courseService.getCoursesByLevel(level)
        );
    }
    @PostMapping("/increase/{courseId}")
    public ResponseEntity increaseCourseCapacity(@PathVariable String courseId, @RequestParam int increment) {
        if (increment <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Increment value must be positive")
            );
        }

        boolean result = courseService.increaseCourseCapacity(courseId, increment);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Course capacity increased successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse("Course not found")
            );
        }
    }
}