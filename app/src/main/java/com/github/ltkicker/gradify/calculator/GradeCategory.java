package com.github.ltkicker.gradify.calculator;

import java.util.HashMap;
import java.util.List;

public class GradeCategory {
    String name;
    int gradePercentage;

    List<HashMap<Integer, Integer>> examList; // <Iteration, MaxScore>

    public GradeCategory(String name, int gradePercentage) {
        this.name = name;
        this.gradePercentage = gradePercentage;
    }


}
