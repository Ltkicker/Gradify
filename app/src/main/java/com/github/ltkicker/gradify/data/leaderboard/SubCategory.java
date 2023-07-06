package com.github.ltkicker.gradify.data.leaderboard;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SubCategory implements Serializable {

    private String name;
    private long date;

    private double maxScore;

    public SubCategory() {

    }

    public String getName() {
        return name;
    }
    public double getMaxScore() {
        return maxScore;
    }

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy H:mm");
        sdf.setTimeZone(TimeZone.getDefault());
        Date date1 = new Date(date * 1000);
        String formattedDate = sdf.format(date1);
        return formattedDate;
    }

    public double getDate() {
        return date;
    }

    public long getDateInUnix() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }
}
