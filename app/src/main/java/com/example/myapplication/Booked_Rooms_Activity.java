package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.Adapters.Booked_Room_Adapter;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Booked_Rooms_Activity extends AppCompatActivity {

    ArrayList<Booking> bookingsList;
    Booked_Room_Adapter booked_room_adapter;
    RecyclerView recyclerView;
    Room room;
    LocalDate date;
    FirebaseFirestore db;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_rooms);
        button2 = findViewById(R.id.button2);

        db = FirebaseFirestore.getInstance();

        bookingsList = new ArrayList<Booking>();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        room = (Room) getIntent().getSerializableExtra("room");
        date = (LocalDate) getIntent().getSerializableExtra("date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);

        String formattedDate = date.format(formatter);

        button2.setText(formattedDate);

        booked_room_adapter = new Booked_Room_Adapter(this, bookingsList,date,room);
        recyclerView.setAdapter(booked_room_adapter);

        db.collection("bookings").whereEqualTo("roomTitle",room.getTitle())
                .get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                bookingsList.clear();
                for (com.google.firebase.firestore.QueryDocumentSnapshot document : task.getResult()) {
                    Booking booking = document.toObject(Booking.class);
                    bookingsList.add(booking);
                }
                booked_room_adapter.notifyDataSetChanged();
            }
        });


    }
}