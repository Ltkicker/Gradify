package com.github.ltkicker.gradify.data.classrooms;

public class UnregisteredSubCat {
    private String SubjectCode;
    private String SubjectSection;
    private String Title;

    private String Date;

    private String Points;


    public UnregisteredSubCat(String subjectCode, String subjectSection, String Title, String date, String points) {
        this.SubjectCode = subjectCode;
        this.SubjectSection = subjectSection;
        this.Title = Title;
        this.Date = date;
        this.Points = points;
    }
}
