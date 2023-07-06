package com.github.ltkicker.gradify.calculator;

public class UniversityGrade {
    public static double convert(double grade) {
        if(grade >= 95 && grade <= 100){
            return 1.0;
        }else if (grade >= 89 && grade < 94) {
            return 1.25;
        } else if (grade >= 83 && grade < 88) {
            return 1.50;
        } else if (grade >=77  && grade < 82) {
            return 1.75;
        } else if (grade >= 71 && grade < 76) {
            return 2.00;
        } else if (grade >= 65 && grade < 70) {
            return 2.25;
        } else if(grade >= 60 && grade < 64) {
            return 2.50;
        } else if(grade >= 55 && grade < 59){
            return 2.75;
        } else if(grade >= 50 && grade < 54){
            return 3.00;
        } else if(grade < 50){
            return 5.00;
        } else {
            return -1.0;
        }
    }
}
