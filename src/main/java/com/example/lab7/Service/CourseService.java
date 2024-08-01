package com.example.lab7.Service;

import com.example.lab7.Models.Course;
import com.example.lab7.Models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class CourseService {




        private ArrayList<Course> courses = new ArrayList<>();

        public ArrayList<Course> getCourses() {
            return courses;
        }

        public void addCourse(Course course) {
            courses.add(course);
        }

        public boolean updateCourse(String id, Course course) {
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getId().equals(id)) {
                    courses.set(i, course);
                    return true;
                }
            }
            return false;
        }

        public boolean removeCourse(String id) {
            if (courses.isEmpty()) {
                return false;
            }

            for (Course course : courses) {
                if (course.getId().equals(id)) {
                    courses.remove(course);
                    return true;
                }
            }
            return false;
        }

        public boolean addStudentToCourse(String courseId, Student student) {
            for (Course course : courses) {
                if (course.getId().equals(courseId)) {
                    if (course.getStudentsCourse() == null) {
                        course.setStudentsCourse(new ArrayList<>());
                    }
                    if (course.getStudentsCourse().size() < course.getCapacity()) {
                        course.getStudentsCourse().add(student);
                        return true;
                    }
                    break;
                }
            }
            return false;
        }

        public boolean removeStudentFromCourse(String courseId, String studentId) {
            for (Course course : courses) {
                if (course.getId().equals(courseId)) {
                    for (Student student : course.getStudentsCourse()) {
                        if (student.getId().equals(studentId)) {
                            course.getStudentsCourse().remove(student);
                            return true;
                        }
                    }
                }
            }
            return false;
        }

    public ArrayList<Course> getCoursesByLevel(String level) {
        ArrayList<Course> coursesByLevel = new ArrayList<>();
        if (courses.isEmpty()) {
            return coursesByLevel; // Return an empty list instead of null
        }

        for (Course course : courses) {
            if (course.getLevel().equalsIgnoreCase(level)) {
                coursesByLevel.add(course);
            }
        }
        return coursesByLevel;
    }

    public boolean increaseCourseCapacity(String courseId, int increment) {
        if (increment <= 0) {
            return false;
        }

        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                course.setCapacity(course.getCapacity() + increment);
                return true;
            }
        }
        return false;
    }

}

