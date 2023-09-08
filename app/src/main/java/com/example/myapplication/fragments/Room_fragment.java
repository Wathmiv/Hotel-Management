package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.myapplication.Adapters.RoomAdapter;
import com.example.myapplication.Edit_Room_info_Activity;
import com.example.myapplication.R;
import com.example.myapplication.models.Room;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class Room_fragment extends Fragment {

    RecyclerView recyclerView;
    ImageButton addRoomBtn;
    ArrayList<Room> roomsList;
    RoomAdapter roomAdapter;
    FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View roomView = inflater.inflate(R.layout.fragment_room_fragment, container, false);
        recyclerView = roomView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = FirebaseFirestore.getInstance();
        roomsList = new ArrayList<>();
        roomAdapter = new RoomAdapter(getActivity(), roomsList);
        recyclerView.setAdapter(roomAdapter);

        db.collection("rooms").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                roomsList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Room room = document.toObject(Room.class);
                    room.setDocumentId(document.getId());
                    roomsList.add(room);
                }
                roomAdapter.notifyDataSetChanged();
            }
        });

        addRoomBtn = roomView.findViewById(R.id.addRoomBtn);

        addRoomBtn.setOnClickListener(v -> {
            // Open the add room activity
            Intent intent = new Intent(getActivity(), Edit_Room_info_Activity.class);
            startActivity(intent);
        });
        return roomView;

    }



}