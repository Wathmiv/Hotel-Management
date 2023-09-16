package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    Context context;
    ArrayList<Task> tasksList;

    public TaskAdapter(Context context, ArrayList<Task> tasksList) {
        this.context = context;
        this.tasksList = tasksList;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        Task task = tasksList.get(position);
        holder.task_name.setText(task.getTaskName());
        holder.task_assignedTo.setText(task.getAssignedTo());

    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView task_name, task_assignedTo;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.task_name);
            task_assignedTo = itemView.findViewById(R.id.task_assignedTo);
        }
    }
}
