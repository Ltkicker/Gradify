package com.github.ltkicker.gradify.data.leaderboard;

public class ParentCategory {

    private String name;
    private double percentage;
    private String key;

    public ParentCategory(String key, String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
        this.key = key;
    }


    public String getName() {
        return name;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getKey() {
        return key;
    }
}