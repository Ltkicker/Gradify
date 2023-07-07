package com.github.ltkicker.gradify.data.leaderboard;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SubCategory implements Serializable {

    private String title;
    private long date;

    private double totalpoints;

    public SubCategory() {

    }

    public String getTitle() {
        return title;
    }
    public double getTotalpoints() {
        return totalpoints;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setTotalpoints(double totalpoints) {
        this.totalpoints = totalpoints;
    }
}
