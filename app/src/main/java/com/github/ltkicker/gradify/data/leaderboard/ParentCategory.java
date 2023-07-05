package com.github.ltkicker.gradify.data.leaderboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ParentCategory {

    private String name;
    private double percentage;

    public ParentCategory(String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
    }


    public String getName() {
        return name;
    }

    public double getPercentage() {
        return percentage;
    }
}