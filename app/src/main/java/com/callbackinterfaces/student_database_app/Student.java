package com.callbackinterfaces.student_database_app;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student {
    //designating attributes for out Student instance.
    @NonNull
    @PrimaryKey
    private String studentID;
    private String studentName;
    private String programme;
    //initialising private members/sqlight student_table attributes.
    public Student( String studentName,String studentID, String programme) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.programme = programme;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getProgramme() {
        return programme;
    }
}
