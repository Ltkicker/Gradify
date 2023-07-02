package com.github.ltkicker.gradify.data.grades;

public class Quiz {
    private String title;
    private double score;

    public Quiz(String title, double score) {
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

    //object - ma create ni siya pag mag create sa front end ug quiz sa category
    Quiz quiz1 = new Quiz("Quiz 1", 40); // dapat ma create ni siya whenever mag buhat ug new quiz
    Quiz quiz2 = new Quiz("Quiz 2", 43);
}