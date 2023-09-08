package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.models.Booking;

public class Booking_info_Activity extends AppCompatActivity {

    TextView room_title, displayName, numberOfGuests, bookingStartDate, bookingEndDate,total_nights, price ;
    Button button;
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

        Booking booking = (Booking) getIntent().getSerializableExtra("booking");

        room_title.setText(booking.getRoomTitle());
        displayName.setText(booking.getBookedBy().get("displayName").toString());
        numberOfGuests.setText(String.valueOf(booking.getNumberOfGuests()));
        bookingStartDate.setText(String.valueOf(booking.getBookingStartDate()));
        bookingEndDate.setText(String.valueOf(booking.getBookingEndDate()));
        total_nights.setText(String.valueOf(booking.getTotalNights()));
        price.setText(String.valueOf(booking.getPrice()));


    }
}