package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Booking2;
import com.example.myapplication.models.Room;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Reserve_Room_Activity extends AppCompatActivity {
    private Booking bookingFromIntent;
    private Booking2 booking;
    private LocalDateTime checkInDateTime, checkOutDateTime;
    private Date start, end;
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
        checkInDateTime = (LocalDateTime) getIntent().getSerializableExtra("checkInDateTime");
        checkOutDateTime = (LocalDateTime) getIntent().getSerializableExtra("checkOutDateTime");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        //start =  (Date) checkOutDateTime;
        ZoneId zoneId = ZoneId.systemDefault(); // You can change the time zone as needed
        start = Date.from(checkOutDateTime.atZone(zoneId).toInstant());
        end = Date.from(checkOutDateTime.atZone(zoneId).toInstant());
        //end = new Timestamp(checkOutDateTime.toEpochSecond(ZoneOffset.UTC)*1000);

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
            setBookingInfo();
            if (bookingFromIntent == null) {
                addBooking();
                Toast.makeText(this, "Booking added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Booking_add_confirmation_dialogue dialogFragment = new Booking_add_confirmation_dialogue();
                // Show the dialog
                dialogFragment.show(getSupportFragmentManager(), "Booking_add_confirmation_dialogue");
            }
        });
    }

    public void setBookingInfo() {
        booking = new Booking2();
        booking.setRoomTitle(room.getTitle());
        booking.setBookedBy(bookedBy);

        booking.setBookingStartDate(new Timestamp(start.getTime()));

        booking.setBookingEndDate(new Timestamp(end.getTime()));

        booking.setNumberOfGuests(room.getMaxGuests());
        booking.setPrice(room.getPricePerNight());
        Toast.makeText(this, "Set booking", Toast.LENGTH_SHORT).show();
    }

    public void addBooking() {
        Toast.makeText(this, "Adding booking", Toast.LENGTH_SHORT).show();
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
