package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.models.Worker;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class Add_Employee extends AppCompatActivity {

    Button add;
    EditText name, staffType;
    Worker worker;
    FirebaseFirestore db;
    Integer id;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_employee_dialogue);
        add = findViewById(R.id.add);
        name = findViewById(R.id.name);
        staffType = findViewById(R.id.staffType);

        db = FirebaseFirestore.getInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();

        add.setOnClickListener(v -> {
            SetEmployee();
            // Add the task to the database
            db.collection("hotelStaff").add(worker).addOnSuccessListener(documentReference -> {
                Toast.makeText(this, "Employee added successfully", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {

            });
        });

    }

    private void SetEmployee() {
        this.worker = new Worker();
        worker.setName(name.getText().toString());
        worker.setStaffType(staffType.getText().toString());
    }
}