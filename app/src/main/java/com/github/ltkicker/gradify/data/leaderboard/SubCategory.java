package com.github.ltkicker.gradify.data.leaderboard;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SubCategory implements Serializable {

    private String name;
    private long date;


    public String getName() {
        return name;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy H:mm");
        sdf.setTimeZone(TimeZone.getDefault());
        Date date1 = new Date(date * 1000);
        String formattedDate = sdf.format(date1);
        return formattedDate;
    }

    public long getDateInUnix() {
        return date;
    }
}
