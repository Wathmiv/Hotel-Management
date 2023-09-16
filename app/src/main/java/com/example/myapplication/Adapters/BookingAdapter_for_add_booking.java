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
                intent.putExtra("booking", bookingsOnRoom);
                intent.putExtra("room", room);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d, yyyy, h:mm:ss a", Locale.ENGLISH);
                LocalTime startTime = LocalTime.of(14, 0, 0);
                LocalTime endTime = LocalTime.of(12, 0, 0);
                //LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
                //LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
                LocalDateTime startDateTime = LocalDateTime.of(2023, 9, 12, 14, 0, 0);
                LocalDateTime endDateTime = LocalDateTime.of(2023, 9, 13, 12, 0, 0);
                String startDateTimeString = startDateTime.format(formatter);
                String endDateTimeString = endDateTime.format(formatter);
                intent.putExtra("checkInDateTime", "Mon, Sep 11, 2023, 02:00:00 PM");
                intent.putExtra("checkOutDateTime", "Wed, Sep 13, 2023, 12:00:00 PM");

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
