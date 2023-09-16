package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.models.Task;
import com.google.firebase.firestore.FirebaseFirestore;


public class Add_Task_Dialogue extends DialogFragment {
    Button addTaskBtn;
    EditText taskTitle, assignedTo, description;
    Task task;
    FirebaseFirestore db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View taskView = inflater.inflate(R.layout.fragment_add_task_dialogue, container, false);
        addTaskBtn = taskView.findViewById(R.id.addTask);
        taskTitle = taskView.findViewById(R.id.editTitle);
        assignedTo = taskView.findViewById(R.id.editEmployee);
        description = taskView.findViewById(R.id.editDescription);

        db = FirebaseFirestore.getInstance();

        addTaskBtn.setOnClickListener(v -> {
            SetTask();
            // Add the task to the database
            db.collection("tasks").add(task).addOnSuccessListener(documentReference -> {

                }).addOnFailureListener(e -> {

            });
            // Close the dialogue
            getDialog().dismiss();
        });

        return taskView;
    }

    public void SetTask() {
        this.task = new Task();
        task.setTaskName(taskTitle.getText().toString());
        task.setAssignedTo(assignedTo.getText().toString());
        task.setDescription(description.getText().toString());
    }
}