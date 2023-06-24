package com.github.ltkicker.gradify.data.grades;

import com.google.firebase.database.FirebaseDatabase;

public class StudentGrade {
    private String assignmentGrade;
    private String examGrade;
    private String projectGrade;

    public StudentGrade(){
    }

    public StudentGrade(String assignmentGrade, String examGrade, String projectGrade){
        this.assignmentGrade = assignmentGrade;
        this.examGrade = examGrade;
        this.projectGrade = projectGrade;
    }

    public String getAssignmentGrade(){
        return assignmentGrade;
    }

    public void setAssignmentGrade(String assignmentGrade){
        this.assignmentGrade = assignmentGrade;
    }

    public String getExamGrade(){
        return examGrade;
    }

    public void setExamGrade(String examGrade){
        this.examGrade = examGrade;
    }

    public String getProjectGrade(){
        return projectGrade;
    }

    public void setProjectGrade(String projectGrade){
        this.projectGrade = projectGrade;
    }




}


