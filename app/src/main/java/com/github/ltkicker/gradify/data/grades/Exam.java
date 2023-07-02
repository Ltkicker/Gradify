package com.github.ltkicker.gradify.data.grades;

public class Exam {
    private String title;
    private double score;

    public Exam(String title, double score) {
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


    //object - ma create ni pag mag create ug new exam sa font end
    Exam exam1 = new Exam("Exam 1", 40); // dapat ma create ni siya whenever mag buhat ug new exam
    Exam exam2 = new Exam("Exam 2", 43);

}
