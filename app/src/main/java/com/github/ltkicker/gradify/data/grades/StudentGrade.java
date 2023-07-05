package com.github.ltkicker.gradify.data.grades;

import java.util.ArrayList;

public class StudentGrade{

        private String name;
        private ArrayList<Double> exams;
        private ArrayList<Double> projects;
        private ArrayList<Integer> attendance;

        public StudentGrade(String name) {
            this.name = name;
            this.exams = new ArrayList<>();
            this.projects = new ArrayList<>();
            this.attendance = new ArrayList<>();
        }

        public void addExamGrade(double grade) {
            exams.add(grade);
        }

}
