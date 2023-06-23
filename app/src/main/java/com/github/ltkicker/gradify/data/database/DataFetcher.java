package com.github.ltkicker.gradify.data.database;

import com.github.ltkicker.gradify.data.classrooms.Classroom;

import java.util.ArrayList;

public interface DataFetcher {
    void onDataLoaded(ArrayList<Classroom> data);
}
