package com.callbackinterfaces.student_database_app;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private StudentDao studentDao;
    private StudentDatabase database;
    private LiveData<List<Student>> allStudents;
    public Repository(Application application) {
        database=StudentDatabase.retrieveInstance(application);
        studentDao=database.getStudentDao();
        allStudents=studentDao.getAllStudents();
    }

    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public StudentDao getRepoStudentDao() {
        return studentDao;
    }
    public void insert(Student student) {
        new InsertAsyncTask(studentDao).execute(student);
    }
    public void delete(Student student) {
        new DeleteAsyncTask(studentDao).execute(student);
    }
    public void deleteAll() {
        new DeleteAllAsyncTask(studentDao).execute();
    }
    public void update(Student student) {
        new UpdateAsyncTask(studentDao).execute(student);
    }

    private class InsertAsyncTask extends AsyncTask<Student,Void,Void> {
        private StudentDao studentDao;
        public InsertAsyncTask(StudentDao studentDao) {
            this.studentDao=studentDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students[0]);
            return null;
        }
    }
    private class DeleteAsyncTask extends AsyncTask<Student,Void,Void> {
        private StudentDao studentDao;
        public DeleteAsyncTask(StudentDao studentDao) {
            this.studentDao=studentDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDao.deleteStudent(students[0]);
            return null;
        }
    }
    private class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void> {
        private StudentDao studentDao;
        public DeleteAllAsyncTask(StudentDao studentDao) {
            this.studentDao=studentDao;
        }
        @Override
        protected Void doInBackground(Void...voids) {
            studentDao.deleteAllStudents();
            return null;
        }
    }
    private class UpdateAsyncTask extends AsyncTask<Student,Void,Void> {
        private StudentDao studentDao;
        public UpdateAsyncTask(StudentDao studentDao) {
            this.studentDao=studentDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDao.updateStudent(students[0]);
            return null;
        }
    }

}
