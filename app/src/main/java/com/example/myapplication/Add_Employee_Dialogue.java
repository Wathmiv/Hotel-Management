package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.models.Task;
import com.example.myapplication.models.Worker;
import com.google.firebase.firestore.FirebaseFirestore;


public class Add_Employee_Dialogue extends AppCompatActivity {
    Button add;
    EditText name, staffType;
    Worker worker;
    FirebaseFirestore db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_employee_dialogue);
        add = findViewById(R.id.add);
        name = findViewById(R.id.name);
        staffType = findViewById(R.id.staffType);

        db = FirebaseFirestore.getInstance();

        add.setOnClickListener(v -> {
            SetEmployee();
            // Add the task to the database
            db.collection("hotelStaff").add(worker).addOnSuccessListener(documentReference -> {
                Toast.makeText(this, "Employee added successfully", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {

            });
        });

    }

    public void SetEmployee() {
        this.worker = new Worker();
        worker.setName(name.getText().toString());
        worker.setStaffType(staffType.getText().toString());
    }
}