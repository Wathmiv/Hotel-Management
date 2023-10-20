package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.OnEmployeeClickListener;
import com.example.myapplication.R;
import com.example.myapplication.models.Worker;

import java.util.ArrayList;

public class Staff_view_Adapter extends RecyclerView.Adapter<Staff_view_Adapter.StaffViewHolder> {

    Context context;
    ArrayList<Worker> workersList;

    private OnEmployeeClickListener onEmployeeClickListener;

    public Staff_view_Adapter(Context context, ArrayList<Worker> workersList) {
        this.context = context;
        this.workersList = workersList;
    }

    public Staff_view_Adapter(Context context, ArrayList<Worker> workersList, OnEmployeeClickListener onEmployeeClickListener) {
        this.context = context;
        this.workersList = workersList;
        this.onEmployeeClickListener = onEmployeeClickListener;
    }

    @NonNull
    @Override
    public Staff_view_Adapter.StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.staff_item, parent, false);
        return new StaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Staff_view_Adapter.StaffViewHolder holder, int position) {
        Worker worker = workersList.get(position);
        holder.staff_name.setText(worker.getName());
        if(onEmployeeClickListener != null) {

        }

    }

    @Override
    public int getItemCount() {
        return workersList.size();
    }

    public class StaffViewHolder extends RecyclerView.ViewHolder {
        TextView staff_name;
        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            staff_name = itemView.findViewById(R.id.staff_name);

            staff_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onEmployeeClickListener != null){
                        onEmployeeClickListener.onEmployeeClick(staff_name.getText().toString());
                    }
                }
            });

        }
    }
}