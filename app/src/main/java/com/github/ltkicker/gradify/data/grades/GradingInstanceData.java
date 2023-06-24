package com.github.ltkicker.gradify.data.grades;

import java.time.Instant;
import java.util.HashMap;

public class GradingInstanceData {
    long unixDate;
    String altName;
    double maxScore;
    HashMap<String, Double> scores;

    public GradingInstanceData() {
        scores = new HashMap<>();
    }

    public GradingInstanceData(String name, double maxScore) {
        this.altName = name;
        this.maxScore = maxScore;
        this.unixDate = Instant.now().getEpochSecond();
        scores = new HashMap<>();
    }
}
