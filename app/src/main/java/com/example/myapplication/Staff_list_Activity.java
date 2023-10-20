package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.Adapters.Staff_view_Adapter;
import com.example.myapplication.models.Worker;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class Staff_list_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Worker> workersList;
    Staff_view_Adapter staff_view_adapter;
    FirebaseFirestore db;
    String staffType;

    TextView staffTypeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);
        recyclerView = findViewById(R.id.recyclerView);
        staffTypeTextView = findViewById(R.id.staffType);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        workersList = new ArrayList<>();
        staff_view_adapter = new Staff_view_Adapter(this, workersList);
        recyclerView.setAdapter(staff_view_adapter);

        staffType = getIntent().getStringExtra("staffType");
        staffTypeTextView.setText(staffType);


        db.collection("hotelStaff").whereEqualTo("staffType",staffType)
                .get().addOnCompleteListener(task -> {
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
}