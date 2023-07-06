package com.github.ltkicker.gradify.data.grades;

public class GradingInstanceData {

    int place;

    String studentId;
    double score;

    public GradingInstanceData(String studentId, double score) {

        this.studentId = studentId;
        this.score = score;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setPlace(int i) {
        this.place = i;
    }

    public int getPlace() {
        return this.place;
    }
}
