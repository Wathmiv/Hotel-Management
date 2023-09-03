package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.models.Room;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class Edit_Room_info_Activity extends AppCompatActivity {

    Button updateBtn;
    EditText titleEditText, descriptionEditText, pricePerNightEditText, numBathsEditText, numBedsEditText,
            maxGuestsEditText, isACEditText,  offersEditText;
    Room room;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room_info);

        titleEditText = findViewById(R.id.title);
        descriptionEditText = findViewById(R.id.description);
        pricePerNightEditText = findViewById(R.id.pricePerNight);
        numBathsEditText = findViewById(R.id.numBaths);
        numBedsEditText = findViewById(R.id.numBeds);
        maxGuestsEditText = findViewById(R.id.maxGuests);
        isACEditText = findViewById(R.id.isAC);
        offersEditText = findViewById(R.id.offers);
        updateBtn = findViewById(R.id.updateBtn);

        // Get the room object from the intent
        room = (Room) getIntent().getSerializableExtra("room");

        titleEditText.setText(room.getTitle());
        descriptionEditText.setText(room.getDescription());
        pricePerNightEditText.setText(String.valueOf(room.getPricePerNight()));
        numBathsEditText.setText(String.valueOf(room.getNumBaths()));
        numBedsEditText.setText(String.valueOf(room.getNumBeds()));
        maxGuestsEditText.setText(String.valueOf(room.getMaxGuests()));
        isACEditText.setText(String.valueOf(room.isAC()));
        offersEditText.setText(room.OffersToString());

        updateBtn.setOnClickListener((v) -> UpdateRoomInfo());

    }

    void UpdateRoomInfo() {
        // Update the room info

        room.setTitle(titleEditText.getText().toString());
        room.setDescription(descriptionEditText.getText().toString());
        room.setPricePerNight(Long.parseLong(pricePerNightEditText.getText().toString()));
        room.setNumBaths(Integer.parseInt(numBathsEditText.getText().toString()));
        room.setNumBeds(Integer.parseInt(numBedsEditText.getText().toString()));
        room.setMaxGuests(Integer.parseInt(maxGuestsEditText.getText().toString()));
        room.setAC(Boolean.parseBoolean(isACEditText.getText().toString()));
        String offersInput = offersEditText.getText().toString();
        String[] offerArray = offersInput.split(","); // Split the input string by commas

        ArrayList<String> newOffers = new ArrayList<>(Arrays.asList(offerArray));
        room.setOffers(newOffers);

        db = FirebaseFirestore.getInstance();
        db.collection("rooms").document(room.getDocumentId()).set(room)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(Edit_Room_info_Activity.this, Room_info_Activity.class);
                        intent.putExtra("room", room);
                        startActivity(intent);
                        // The document has been successfully updated
                        // You can perform any necessary UI updates or show a success message here
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occurred during the update
                        // You can show an error message or log the error here
                        Toast.makeText(Edit_Room_info_Activity.this, "Error updating room: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
        }


        // If the room info is updated successfully, then open the room info activity


        // Else, show an error message
    }
