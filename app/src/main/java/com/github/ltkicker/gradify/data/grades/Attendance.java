package com.github.ltkicker.gradify.data.grades;

public class Attendance {
    private String title;
    private double score;

    public Attendance(String title, double score) {
        this.title = title;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
