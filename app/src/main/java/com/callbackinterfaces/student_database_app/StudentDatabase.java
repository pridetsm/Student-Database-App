package com.callbackinterfaces.student_database_app;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class},version = 1)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase database;
    public abstract StudentDao getStudentDao();
   public static synchronized StudentDatabase retrieveInstance(Context context) {
       if(database==null) {
           database=Room.databaseBuilder(context,StudentDatabase.class,"student_database")
                   .addCallback(callback)
                   .fallbackToDestructiveMigrationFrom()
                   .build();

       }
       return database;
   }
   private static RoomDatabase.Callback callback=new Callback() {
       @Override
       public void onCreate(@NonNull SupportSQLiteDatabase db) {
           super.onCreate(db);
           new PopulateDBAsyncTask(database).execute();
       }
   };
   private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {

       private StudentDao studentDao;
       public PopulateDBAsyncTask(StudentDatabase database) {
           studentDao=database.getStudentDao();
       }
       @Override
       protected Void doInBackground(Void... voids) {
           studentDao.insertStudent(new Student("John Snow","NightsWatch","Computer Science"));
           studentDao.insertStudent(new Student("Denearis Targerian","Dracaris","Computer Science"));
           studentDao.insertStudent(new Student("The Hound","2 chickens","Computer Science"));
           studentDao.insertStudent(new Student("The Mountain","4 chickens","Computer Science"));
           studentDao.insertStudent(new Student("Tirion Lannister","Close the door","Computer Science"));

           return null;
       }
   }

}
