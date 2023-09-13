package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.fragments.Bookings_fragment;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;

public class Booking_info_Activity extends AppCompatActivity {

    TextView room_title, displayName, numberOfGuests, bookingStartDate, bookingEndDate,total_nights, price ;
    Button button;
    ImageView go_back;

    Booking booking;
    Room room;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_info);

        room_title = findViewById(R.id.room_title);
        displayName = findViewById(R.id.displayName);
        numberOfGuests = findViewById(R.id.numberOfGuests);
        bookingStartDate = findViewById(R.id.bookingStartDate);
        bookingEndDate = findViewById(R.id.bookingEndDate);
        total_nights = findViewById(R.id.total_nights);
        price = findViewById(R.id.price);

        button = findViewById(R.id.button);
        go_back = findViewById(R.id.go_back);

        booking = (Booking) getIntent().getSerializableExtra("booking");
        room = (Room) getIntent().getSerializableExtra("room");

        room_title.setText(booking.getRoomTitle());
        displayName.setText(booking.getBookedBy().get("displayName").toString());
        numberOfGuests.setText(String.valueOf(booking.getNumberOfGuests()));
        bookingStartDate.setText(String.valueOf(booking.getBookingStartDate()));
        bookingEndDate.setText(String.valueOf(booking.getBookingEndDate()));
        total_nights.setText(String.valueOf(booking.getTotalNights()));
        price.setText(String.valueOf(booking.getPrice()));

        button.setOnClickListener(v -> {
            clickRoomInfoActivity();
        });
        go_back.setOnClickListener(v -> {
            clickGoBack();
        });


    }

    public void clickRoomInfoActivity() {
        // Open the room info activity
        Intent intent = new Intent(this, Room_info_Activity.class);
        intent.putExtra("room", room);
        startActivity(intent);
    }

    public void clickGoBack() {
        onBackPressed();
    }
}