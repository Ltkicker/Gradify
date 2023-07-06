package com.github.ltkicker.gradify.data.database;

import com.github.ltkicker.gradify.data.leaderboard.SubCategory;

import java.util.ArrayList;

public interface SubCategFromParentListener {
    void onFetch(ArrayList<SubCategory> data);
}
