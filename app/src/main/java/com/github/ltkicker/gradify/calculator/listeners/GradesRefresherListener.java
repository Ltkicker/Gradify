package com.github.ltkicker.gradify.calculator.listeners;

import com.github.ltkicker.gradify.data.grades.UserStandingData;

import java.util.HashMap;

public interface GradesRefresherListener {
    public void onRefresh(HashMap<String, UserStandingData> data);
}
