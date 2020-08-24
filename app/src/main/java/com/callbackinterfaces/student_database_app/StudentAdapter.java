package com.callbackinterfaces.student_database_app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    public interface EditStudentIntentListener {
        void onReceiveIntent(Intent intent);
    }
    private EditStudentIntentListener intentListener;
    public StudentAdapter(EditStudentIntentListener intentListener) {
        this.intentListener=intentListener;
    }
    private List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }
    public Student getStudentAt(int position) {
        return students.get(position);
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false),null);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, final int position) {
        holder.studentName.setText(students.get(position).getStudentName());
        holder.studentID.setText(String.valueOf(students.get(position).getStudentID()));
        holder.programme.setText(students.get(position).getProgramme());
        holder.listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(((MainActivity)intentListener),AddEditStudentActivity.class);
                intent.putExtra("REQUEST_SPEC","Edit Student");
                intent.putExtra("STD_NAME",students.get(position).getStudentName());
                intent.putExtra("STD_ID",students.get(position).getStudentID());
                intent.putExtra("STD_PROGRAMME",students.get(position).getProgramme());
                intentListener.onReceiveIntent(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return students!=null? students.size():0;
    }

    public class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView studentName;
        private TextView studentID;
        private  TextView programme;
        private View.OnClickListener listener;
        public StudentHolder(@NonNull View itemView,View.OnClickListener listener) {
            super(itemView);
            studentName=itemView.findViewById(R.id.student);
            studentID=itemView.findViewById(R.id.id);
            programme=itemView.findViewById(R.id.programme);
            this.listener=listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v);
        }
    }
}
