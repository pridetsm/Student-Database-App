# Student-Database-App

This is a simple android application, designed to run on all versions of Android from Marshmallow going upwards.
The app is built following the MVVM architecture pattern and makes use of the Room Api which is 
part of Android Architecture Components.
The app is meant to serve as a basic introduction to the use of the sqlight database within your app.
It consists of a database of students which already has 4 students from the Game of Thrones Institute of Technology.
A card layout is used to present each student's information (Name,ID,Programme) on a recyclerView.
When each item on this recyclerView is clicked the AddEditStudent activity is started "for result" to edit the info
related to that student.
There is also an add fab to add new students to the database, and to delete a student syply swipe the wcard on the recycler view.
If a student with the same std id/primary key is added the app simply replaces the old student with the new.
This means when you are editing the student information if you change the std id then a new student will be created in the database.
The recyclerView layed out by main activity also makes use of another api from the Android Architecture Components called LiveData.
This library is used to wrap the data we retrieve from the database there by allowing us to 'observe' that data.This simply means
if any part of our app makes any changes in the database the recyclerView Will immediately be alerted and updated accordingly all at runtime
within the same 'process'.
 

