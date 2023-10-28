package com.example.myapplication;


import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapters.Room_Images_Adapter;
import com.example.myapplication.models.Room;

import java.util.ArrayList;
import java.util.List;

public class Room_info_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    Room_Images_Adapter roomImagesAdapter;
    List<String> imagesList;
    ImageView editIconImageView, goBackImageView;

    TextView titleEditText, descriptionEditText, pricePerNightEditText, numBathsEditText, numBedsEditText,
            maxGuestsEditText, isACEditText, offersEditText, numRoomsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);

        titleEditText = findViewById(R.id.title);
        descriptionEditText = findViewById(R.id.description);
        pricePerNightEditText = findViewById(R.id.pricePerNight);
        numBathsEditText = findViewById(R.id.numBaths);
        numBedsEditText = findViewById(R.id.numBeds);
        maxGuestsEditText = findViewById(R.id.maxGuests);
        isACEditText = findViewById(R.id.isAC);
        offersEditText = findViewById(R.id.offers);
        editIconImageView = findViewById(R.id.imageView5);
        goBackImageView = findViewById(R.id.imageView4);
        numRoomsTextView = findViewById(R.id.numRooms);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Room room = (Room) getIntent().getSerializableExtra("room");

        titleEditText.setText(room.getTitle());
        descriptionEditText.setText(room.getDescription());
        pricePerNightEditText.setText(String.valueOf(room.getPricePerNight()));
        numBathsEditText.setText(String.valueOf(room.getNumBaths()));
        numBedsEditText.setText(String.valueOf(room.getNumBeds()));
        maxGuestsEditText.setText(String.valueOf(room.getMaxGuests()));
        isACEditText.setText(String.valueOf(room.getIsAC()));
        offersEditText.setText(room.OffersToString());
        numRoomsTextView.setText(String.valueOf(room.getNumRooms()));
        imagesList = room.getImages();

        roomImagesAdapter = new Room_Images_Adapter(imagesList, this);
        recyclerView.setAdapter(roomImagesAdapter);

        editIconImageView.setOnClickListener(v -> {
            // Open the edit room info activity
            Intent intent = new Intent(this, Edit_Room_info_Activity.class);
            intent.putExtra("room", room);
            startActivity(intent);
        });

        goBackImageView.setOnClickListener(v -> {
            // Go back to the main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Intent intent = new Intent(Room_info_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }



}