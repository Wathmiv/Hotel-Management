package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.Adapters.TaskAdapter;
import com.example.myapplication.Add_Task_Activity;
import com.example.myapplication.Add_Task_Dialogue;
import com.example.myapplication.R;
import com.example.myapplication.models.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class Task_fragment extends Fragment {

    ArrayList<Task> tasksList;
    TaskAdapter taskAdapter;
    FirebaseFirestore db;
    RecyclerView recyclerView;

    LinearLayout addTask;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View taskView = inflater.inflate(R.layout.fragment_tasks, container, false);

        recyclerView = taskView.findViewById(R.id.recyclerView);
        addTask = taskView.findViewById(R.id.addTask);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = FirebaseFirestore.getInstance();
        tasksList = new ArrayList<>();
        taskAdapter = new TaskAdapter(getActivity(), tasksList);
        recyclerView.setAdapter(taskAdapter);

        db.collection("tasks").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                tasksList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Task taskObject = document.toObject(Task.class);
                    tasksList.add(taskObject);
                }
                taskAdapter.notifyDataSetChanged();
            }
        });

        addTask.setOnClickListener(v -> {
            /*
            Add_Task_Dialogue dialogFragment = new Add_Task_Dialogue();
            // Show the dialog
            dialogFragment.show(getActivity().getSupportFragmentManager(), "Add_Task_Dialogue");*/

            Intent intent = new Intent(getActivity(), Add_Task_Activity.class);
            startActivity(intent);
        });
        return taskView;
    }

}