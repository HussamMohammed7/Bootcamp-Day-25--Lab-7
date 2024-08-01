package com.example.lab7.Service;

import com.example.lab7.Models.School;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SchoolService {

    private ArrayList<School> schools = new ArrayList<>();

    public ArrayList<School> getSchools() {
        return schools;
    }

    public void addSchool(School school) {
        schools.add(school);
    }

    public boolean updateSchool(String id, School school) {
        for (int i = 0; i < schools.size(); i++) {
            if (schools.get(i).getId().equals(id)) {
                schools.set(i, school);
                return true;
            }
        }
        return false;
    }

    public boolean removeSchool(String id) {
        if (schools.isEmpty()) {
            return false;
        }

        for (School school : schools) {
            if (school.getId().equals(id)) {
                schools.remove(school);
                return true;
            }
        }
        return false;
    }
    public int getNumberOfStudentsBySchoolId(String schoolId) {
        for (School school : schools) {
            if (school.getId().equals(schoolId)) {
                return school.getNumberOfStudents();
            }
        }
        return -1;
    }
    public ArrayList<School> getSchoolsByOnlineStatus(boolean isOnline) {
        ArrayList<School> filteredSchools = new ArrayList<>();

        for (School school : schools) {
            if (school.isOnlineSchool() == isOnline) {
                filteredSchools.add(school);
            }
        }

        return filteredSchools.isEmpty() ? null : filteredSchools;
    }


    public boolean updateSchoolOnlineStatus(String schoolId, boolean isOnline) {
        for (School school : schools) {
            if (school.getId().equals(schoolId)) {
                school.setOnlineSchool(isOnline);
                return true;
            }
        }
        return false;
    }

    public ArrayList<School> getSchoolsByType(String type) {
        ArrayList<School> filteredSchools = new ArrayList<>();

        if (schools.isEmpty()) {
            return null;
        }

        for (School school : schools) {
            if (school.getType().equalsIgnoreCase(type)) {
                filteredSchools.add(school);
            }
        }

        return filteredSchools.isEmpty() ? null : filteredSchools;
    }

}
