package com.github.ltkicker.gradify.data.grades;

public class Subclass {
    int Score;
    int totalScore;
     double parentClassPercent;

    public Subclass(){

    }

    public void setScore(int score) { this.Score = score;}

    public void setTotalScore(int totalScore) {this.totalScore = totalScore; }

    public int getSore() {
        return Score;
    }

    public int getTotalScore() {return totalScore; }

    public void setParentClassPercent(double parentClassPercent) { this.parentClassPercent = parentClassPercent; }

    public double getParentClassPercent() { return parentClassPercent; }

    private double calculateScorePercentage(){
         //
        return 0.0;
    }

}
