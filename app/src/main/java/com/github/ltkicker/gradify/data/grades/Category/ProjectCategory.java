package com.github.ltkicker.gradify.data.grades.Category;
import com.github.ltkicker.gradify.data.grades.Project;


import java.util.ArrayList;
import java.util.List;


public class ProjectCategory extends Project {
    private List<Project> project = new ArrayList<>();

    public ProjectCategory(String title, double score) {
        super(title, score);
    }


}
