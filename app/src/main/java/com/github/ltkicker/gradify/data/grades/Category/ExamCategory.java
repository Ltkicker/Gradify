package com.github.ltkicker.gradify.data.grades.Category;
import com.github.ltkicker.gradify.data.grades.Exam;


import java.util.ArrayList;
import java.util.List;


public class ExamCategory extends Exam {
    private List<Exam> exam = new ArrayList<>();

    public ExamCategory(String title, double score) {
        super(title, score);
    } // i connect pa ni sa firebase
}
