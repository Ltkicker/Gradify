package com.github.ltkicker.gradify.data.grades;

public class Category {
    String name;
    double percentage;
    private static double totalPrecentageFromChild() {
        return 0.0;
    }

    public Category(){
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getName(){
        return name;
    }

    public double getPercentage(){
        return percentage;
    }

    public void calculateTotalPercentageFromChildren(){

    }
}
