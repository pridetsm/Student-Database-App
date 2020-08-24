package com.callbackinterfaces.student_database_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);
    @Update
    void updateStudent(Student student);
    @Delete
    void deleteStudent(Student student);
    @Query("DELETE FROM student_table")
    void deleteAllStudents();
    @Query("SELECT * FROM student_table")
    LiveData<List<Student>> getAllStudents();
}
