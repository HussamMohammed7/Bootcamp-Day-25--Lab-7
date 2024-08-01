package com.example.lab7.Service;


import com.example.lab7.Models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {



    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students ;
    }


    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(String id ,Student student) {

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;

    }

    public boolean removeStudent(String id ) {
        if (students.isEmpty()){
            return false;
        }

        for(Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                return true;
            }
        }
        return false;


    }

    public ArrayList<Student> getStudentsByCity(String city) {
        ArrayList<Student> studentsByCity = new ArrayList<>();
        if (students.isEmpty()) {
            return null;
        }

        for (Student student : students) {
            if (student.getCity().equalsIgnoreCase(city)) {
                studentsByCity.add(student);
            }
        }
        return studentsByCity;
    }

    public ArrayList<Student> getStudentsByAge(int minAge, int maxAge) {
        ArrayList<Student> studentsByAge = new ArrayList<>();
        if (students.isEmpty()) {
            return null;
        }

        for (Student student : students) {
            if (student.getAge() >= minAge && student.getAge() <= maxAge) {
                studentsByAge.add(student);
            }
        }
        return studentsByAge;
    }

    public ArrayList<Student> getStudentsByGraduateStatus(String status) {
        ArrayList<Student> studentsByGraduateStatus = new ArrayList<>();
        if (students.isEmpty()) {
            return null;
        }

        for (Student student : students) {
            if (student.getGraduate().equalsIgnoreCase(status)) {
                studentsByGraduateStatus.add(student);
            }
        }
        return studentsByGraduateStatus;
    }

    public ArrayList<Student> getExcellentStudents() {
        ArrayList<Student> excellentStudents = new ArrayList<>();
        if (students.isEmpty()) {
            return null;
        }

        for (Student student : students) {
            if (student.getGpa() >= 4.5) {
                excellentStudents.add(student);
            }
        }
        return excellentStudents;
    }




}
