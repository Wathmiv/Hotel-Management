package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.myapplication.Booked_Rooms_Activity;
import com.example.myapplication.Booking_info_Activity;
import com.example.myapplication.Controllers.Booking_controller;
import com.example.myapplication.Not_Booked_Activity;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;  // Import LocalDateTime
import java.util.ArrayList;

import kotlin.Pair;

public class BookingAdapter_for_booking_fragment extends BookingAdapter {
    private Booking_controller booking_controller;
    private LocalDate date;

    public BookingAdapter_for_booking_fragment(Context context, ArrayList<Booking> bookingsList, ArrayList<Room> roomsList, LocalDate date) {
        super(context, bookingsList, roomsList);
        this.date = date;
        booking_controller = new Booking_controller(bookingsList);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Room room = roomsList.get(position);
        holder.room_title.setText(room.getTitle());

        ArrayList<LocalDate> dateList = new ArrayList<>();
        dateList.add(date);

        ArrayList<Booking> bookingsOnRoom = booking_controller.isRoomBookedOn(dateList, room);
        if (!bookingsOnRoom.isEmpty()) {
            if (bookingsOnRoom.size() == room.getNumRooms()) {
                holder.room_status.setText("All Rooms are booked");
            } else {
                holder.room_status.setText(("Booked rooms : "+String.valueOf(bookingsOnRoom.size())));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Booked_Rooms_Activity.class);
                    intent.putExtra("room", room);
                    intent.putExtra("date", date);
                    context.startActivity(intent);
                }
            });
        } else {
            holder.room_status.setText("All rooms are Available");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Not_Booked_Activity.class);
                    intent.putExtra("room", room);
                    context.startActivity(intent);
                }
            });
        }
    }

    public void updateDate(LocalDate date) {
        this.date = date;
        notifyDataSetChanged();
    }
}

