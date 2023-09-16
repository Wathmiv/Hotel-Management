package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Not_Booked_Activity extends AppCompatActivity {

    ImageView go_back;

    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_booked);

        go_back = findViewById(R.id.go_back);

        go_back.setOnClickListener(v -> {
            clickGoBack();
        });

        button3 = findViewById(R.id.button3);

        button3.setOnClickListener(v -> {
            clickButton3();
        });
    }

    private void clickGoBack() {
        onBackPressed();
    }

    private void clickButton3() {
        Intent intent = new Intent(this, Add_Booking_Activity.class);
        startActivity(intent);
    }
}