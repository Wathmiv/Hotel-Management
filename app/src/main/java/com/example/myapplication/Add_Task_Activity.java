package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapters.Staff_view_Adapter;
import com.example.myapplication.models.Task;
import com.example.myapplication.models.Worker;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class Add_Task_Activity extends AppCompatActivity implements OnEmployeeClickListener {
    RecyclerView recyclerView;
    ArrayList<Worker> workersList;
    Staff_view_Adapter staff_view_adapter;
    FirebaseFirestore db;
    String staffType;
    Button addTaskBtn;
    EditText taskTitle, description, completeBefore;
    Task task;

    EditText employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        employee = findViewById(R.id.editEmployee);
        taskTitle = findViewById(R.id.title);
        description = findViewById(R.id.description);
        addTaskBtn = findViewById(R.id.updateBtn);
        completeBefore = findViewById(R.id.date);


        db = FirebaseFirestore.getInstance();
        workersList = new ArrayList<>();
        staff_view_adapter = new Staff_view_Adapter(this, workersList, this);
        recyclerView.setAdapter(staff_view_adapter);

        addTaskBtn.setOnClickListener(v -> {
            SetTask();
            // Add the task to the database
            db.collection("tasks").add(task).addOnSuccessListener(documentReference -> {
                Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {

            });
            // Close the dialogue
        });

        db.collection("hotelStaff").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                workersList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Worker worker = document.toObject(Worker.class);
                    workersList.add(worker);
                }
                staff_view_adapter.notifyDataSetChanged();
            }
        });


    }

    public void SetTask() {
        this.task = new Task();
        task.setTaskName(taskTitle.getText().toString());
        task.setAssignedTo(employee.getText().toString());
        task.setDescription(description.getText().toString());
        task.setCompleteBefore(completeBefore.getText().toString());
        task.setStatus("Not completed");
    }

    @Override
    public void onEmployeeClick(String name) {
        employee.setText(name);
    }
}