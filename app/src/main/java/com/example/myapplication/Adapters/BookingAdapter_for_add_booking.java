package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.myapplication.Controllers.Booking_controller;
import com.example.myapplication.Reserve_Room_Activity;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import kotlin.Pair;

public class BookingAdapter_for_add_booking extends BookingAdapter {

    private Booking_controller booking_controller;
    private LocalDate startDate, endDate;
    private ArrayList<LocalDate> date = new ArrayList<>();
    public BookingAdapter_for_add_booking(Context context, ArrayList<Booking> bookingsList, ArrayList<Room> roomsList, LocalDate startDate, LocalDate endDate) {
        super(context, bookingsList, roomsList);
        this.startDate = startDate;
        this.endDate = endDate;
        booking_controller = new Booking_controller(bookingsList);

    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position) {
        Room room = roomsList.get(position);
        holder.room_title.setText(room.getTitle());

        daysBetween(startDate, endDate);
        ArrayList<Booking> bookingsOnRoom = booking_controller.isRoomBookedOn(date, room);


        if (!bookingsOnRoom.isEmpty()) {
            holder.room_status.setText("Booked");
        } else {
            holder.room_status.setText("Available");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Reserve_Room_Activity.class);
                if(!bookingsOnRoom.isEmpty()) {
                    intent.putExtra("booking", bookingsOnRoom.get(0));
                }
                intent.putExtra("room", room);

                LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(14, 0));
                LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(12, 0));


                intent.putExtra("checkInDateTime", startDateTime);
                intent.putExtra("checkOutDateTime", endDateTime);

                context.startActivity(intent);
            }
        });
    }

    public void daysBetween(LocalDate startDate, LocalDate endDate) {
        this.date.clear();
        LocalDate date1 = startDate;
        while (date1.isBefore(endDate)) {
            this.date.add(date1);
            date1 = date1.plusDays(1);
        }
    }

    public void updateDates(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        notifyDataSetChanged();
    }

}
