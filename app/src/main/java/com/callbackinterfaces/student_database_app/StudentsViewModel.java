package com.callbackinterfaces.student_database_app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentsViewModel extends AndroidViewModel {
    private Repository repo;

    private LiveData<List<Student>> allStudents;
    public StudentsViewModel(@NonNull Application application) {
        super(application);
        repo=new Repository(application);
        allStudents=repo.getAllStudents();
    }
    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }
    public void insert(Student student) {
        repo.insert(student);
    }
    public void delete(Student student) {
        repo.delete(student);
    }
    public void update(Student student) {
        repo.delete(student);
    }
    public void deleteAll() {
        repo.deleteAll();
    }


}
