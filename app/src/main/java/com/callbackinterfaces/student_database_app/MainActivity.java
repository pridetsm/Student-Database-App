package com.callbackinterfaces.student_database_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.EditStudentIntentListener {
    private RecyclerView recyclerView;
    private ViewModel viewModel;
    private FloatingActionButton button;
    private static final int ADD_EDIT_REQUEST=1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        button=findViewById(R.id.add_button);
        final StudentAdapter adapter=new StudentAdapter(this);
        recyclerView.setAdapter(adapter);
        viewModel=new ViewModelProvider(this).get(StudentsViewModel.class);
        ((StudentsViewModel)viewModel).getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                adapter.setStudents(students);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

               ((StudentsViewModel) viewModel).delete(adapter.getStudentAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Student deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddEditStudentActivity.class);
                intent.putExtra("REQUEST_SPEC","Add Student");
                startActivityForResult(intent,ADD_EDIT_REQUEST);
            }
        });


    }

    @Override
    public void onReceiveIntent(Intent intent) {
        startActivityForResult(intent,ADD_EDIT_REQUEST);
    }
}
