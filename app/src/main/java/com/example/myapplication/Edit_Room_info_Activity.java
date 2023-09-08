package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.models.Room;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class Edit_Room_info_Activity extends AppCompatActivity {

    Button updateBtn;
    TextView addOrEditTextView;
    EditText titleEditText, descriptionEditText, pricePerNightEditText, numBathsEditText, numBedsEditText,
            maxGuestsEditText, isACEditText,  offersEditText;
    Room room;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room_info);

        db = FirebaseFirestore.getInstance();

        titleEditText = findViewById(R.id.title);
        descriptionEditText = findViewById(R.id.description);
        pricePerNightEditText = findViewById(R.id.pricePerNight);
        numBathsEditText = findViewById(R.id.numBaths);
        numBedsEditText = findViewById(R.id.numBeds);
        maxGuestsEditText = findViewById(R.id.maxGuests);
        isACEditText = findViewById(R.id.isAC);
        offersEditText = findViewById(R.id.offers);
        updateBtn = findViewById(R.id.updateBtn);
        addOrEditTextView = findViewById(R.id.addOrEdit);

        // Get the room object from the intent
        room = (Room) getIntent().getSerializableExtra("room");



        if(room != null) {
            // The room is being edited
            addOrEditTextView.setText("Edit Room");
            updateBtn.setText("Update");
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
        else {
            // The room is being added
            room = new Room();
            updateBtn.setOnClickListener((v) -> AddRoom());
        }



    }

    void UpdateRoomInfo() {
        // Update the room info


        SetRoomInfo();
        db.collection("rooms").document(room.getDocumentId()).set(room)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // The document has been successfully updated
                        Intent intent = new Intent(Edit_Room_info_Activity.this, Room_info_Activity.class);
                        intent.putExtra("room", room);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occurred during the update
                        Toast.makeText(Edit_Room_info_Activity.this, "Error updating room: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
        }

        public void AddRoom(){

        SetRoomInfo();
            db.collection("rooms")
                    .add(room)
                    .addOnSuccessListener(documentReference -> {
                        // Item added successfully
                        String itemId = documentReference.getId();
                        room.setDocumentId(itemId);
                        Toast.makeText(Edit_Room_info_Activity.this, "Room added successfully", Toast.LENGTH_SHORT).show();

                    })
                    .addOnFailureListener(e -> {
                        // Handle the error
                        Toast.makeText(Edit_Room_info_Activity.this, "Error adding room: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    });

            Intent intent = new Intent(Edit_Room_info_Activity.this, MainActivity.class);
            startActivity(intent);
        }

        public void SetRoomInfo(){
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


    }


}
