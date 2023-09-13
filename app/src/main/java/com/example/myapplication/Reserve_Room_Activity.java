package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Reserve_Room_Activity extends AppCompatActivity {
    private Booking bookingFromIntent;
    private Booking booking;
    private String checkInDateTime, checkOutDateTime;
    private Room room;
    ImageView goBackImageView;
    private Button reserveBtn;
    Map<String, String> bookedBy;

    FirebaseFirestore db;
    TextView titleEditText, descriptionEditText, pricePerNightEditText, numBathsEditText, numBedsEditText,
            maxGuestsEditText, isACEditText, statusEditText, offersEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_room);

        titleEditText = findViewById(R.id.title);
        descriptionEditText = findViewById(R.id.description);
        pricePerNightEditText = findViewById(R.id.pricePerNight);
        numBathsEditText = findViewById(R.id.numBaths);
        numBedsEditText = findViewById(R.id.numBeds);
        maxGuestsEditText = findViewById(R.id.maxGuests);
        isACEditText = findViewById(R.id.isAC);
        statusEditText = findViewById(R.id.status);
        offersEditText = findViewById(R.id.offers);
        goBackImageView = findViewById(R.id.imageView);
        reserveBtn = findViewById(R.id.button4);

        room = (Room) getIntent().getSerializableExtra("room");
        bookingFromIntent = (Booking) getIntent().getSerializableExtra("booking");
        checkInDateTime = getIntent().getStringExtra("checkInDateTime");
        checkOutDateTime = getIntent().getStringExtra("checkOutDateTime");

        bookedBy = new HashMap<>();
        bookedBy.put("displayName", "Hotel Manager");
        bookedBy.put("uid", null);

        titleEditText.setText(room.getTitle());
        descriptionEditText.setText(room.getDescription());
        pricePerNightEditText.setText(String.valueOf(room.getPricePerNight()));
        numBathsEditText.setText(String.valueOf(room.getNumBaths()));
        numBedsEditText.setText(String.valueOf(room.getNumBeds()));
        maxGuestsEditText.setText(String.valueOf(room.getMaxGuests()));
        isACEditText.setText(String.valueOf(room.getIsAC()));
        if (bookingFromIntent != null) {
            statusEditText.setText("Booked");
        } else {
            statusEditText.setText("Available");
        }
        offersEditText.setText(room.OffersToString());

        reserveBtn.setOnClickListener(v -> {
            if (bookingFromIntent == null) {
                addBooking();
            } else {
                setBookingInfo();
                Toast.makeText(this, booking.getBookingStartDate(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setBookingInfo() {
        booking = new Booking();
        booking.setRoomTitle(room.getTitle());
        booking.setBookedBy(bookedBy);

        booking.setBookingStartDate(checkInDateTime);

        booking.setBookingEndDate(checkOutDateTime);

        booking.setNumberOfGuests(room.getMaxGuests());
        booking.setPrice(room.getPricePerNight());
        Toast.makeText(this, "Set booking", Toast.LENGTH_SHORT).show();
    }

    public void addBooking() {
        Toast.makeText(this, "Adding booking", Toast.LENGTH_SHORT).show();
        setBookingInfo();
        db = FirebaseFirestore.getInstance();
        db.collection("bookings")
                .add(booking)
                .addOnSuccessListener(documentReference -> {
                    booking.setDocumentId(documentReference.getId());
                    Toast.makeText(this, "Booking added successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error in adding booking", Toast.LENGTH_SHORT).show());
        Intent intent = new Intent(Reserve_Room_Activity.this, MainActivity.class);
        startActivity(intent);
    }
}
