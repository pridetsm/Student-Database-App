package com.callbackinterfaces.student_database_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddEditStudentActivity extends AppCompatActivity {
    private String title;
    private Button done;
    private EditText programme;
    private EditText name;
    private EditText id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_activity_layout);
        done=findViewById(R.id.done);
        programme=findViewById(R.id.programme_edit_text);
        name=findViewById(R.id.std_name_edit_text);
        id=findViewById(R.id.std_id_edit_text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        title=getIntent().getStringExtra("REQUEST_SPEC");
        final StudentsViewModel viewModel=new ViewModelProvider(this).get(StudentsViewModel.class);
        getSupportActionBar().setTitle(title);
        if(getIntent().getStringExtra("REQUEST_SPEC").equals("Edit Student")) {
            programme.setText(getIntent().getStringExtra("STD_PROGRAMME"));
            name.setText(getIntent().getStringExtra("STD_NAME"));
            id.setText(getIntent().getStringExtra("STD_ID"));
        }
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(programme.getText().toString().isEmpty()||name.getText().toString().isEmpty()||id.getText().toString().isEmpty()) {
                    Toast.makeText(AddEditStudentActivity.this, "Please fill in all spaces", Toast.LENGTH_SHORT).show();
                }else {
                    viewModel.insert(new Student(name.getText().toString().trim(),id.getText().toString().trim(),programme.getText().toString().trim()));
                    setResult(RESULT_OK,null);
                    Toast.makeText(AddEditStudentActivity.this, "student added", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
