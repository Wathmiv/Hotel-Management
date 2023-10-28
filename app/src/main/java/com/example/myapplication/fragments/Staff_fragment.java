package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.Add_Employee;
import com.example.myapplication.Add_Employee_Dialogue;
import com.example.myapplication.R;


public class Staff_fragment extends Fragment {

    private CardView cardView_chef,cardView_roomAttendant, cardView_admin, cardView_cleaner;
    private LinearLayout addEmployee;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View staffView = inflater.inflate(R.layout.fragment_staff, container, false);

        cardView_chef = staffView.findViewById(R.id.cardView1);
        cardView_roomAttendant = staffView.findViewById(R.id.cardView2);
        cardView_admin = staffView.findViewById(R.id.cardView3);
        cardView_cleaner = staffView.findViewById(R.id.cardView4);
        addEmployee = staffView.findViewById(R.id.addEmployee);

        cardView_chef.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.example.myapplication.Staff_list_Activity.class);
            intent.putExtra("staffType","Chef");
            getActivity().startActivity(intent);

        });
        cardView_roomAttendant.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.example.myapplication.Staff_list_Activity.class);
            intent.putExtra("staffType","Room Attendant");
            getActivity().startActivity(intent);

        });
        cardView_cleaner.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.example.myapplication.Staff_list_Activity.class);
            intent.putExtra("staffType","Cleaning Staff");
            getActivity().startActivity(intent);

        });
        cardView_admin.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.example.myapplication.Staff_list_Activity.class);
            intent.putExtra("staffType","Admin");
            getActivity().startActivity(intent);

    });

        addEmployee.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Add_Employee.class);
            startActivity(intent);
        });
    return staffView;
    }
}