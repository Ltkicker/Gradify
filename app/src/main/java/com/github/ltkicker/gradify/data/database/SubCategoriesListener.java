package com.github.ltkicker.gradify.data.database;

import com.github.ltkicker.gradify.data.leaderboard.ParentCategory;
import com.github.ltkicker.gradify.data.leaderboard.SubCategory;

import java.util.ArrayList;
import java.util.HashMap;

public interface SubCategoriesListener {
    void onFetch(HashMap<ParentCategory, SubCategory> subCategories);
    void onCancel(String error);
}
