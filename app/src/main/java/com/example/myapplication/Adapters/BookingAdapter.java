package com.example.myapplication.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Booking_info_Activity;
import com.example.myapplication.Controllers.Booking_controller;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Not_Booked_Activity;
import com.example.myapplication.R;
import com.example.myapplication.Room_info_Activity;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;

import java.time.LocalDate;
import java.util.ArrayList;

import kotlin.Pair;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    Context context;
    ArrayList<Booking> bookingsList;
    ArrayList<Room> roomsList;
    LocalDate date;
    Booking_controller booking_controller;

    public BookingAdapter(Context context, ArrayList<Booking> bookingsList, ArrayList<Room> roomsList, LocalDate date) {
        this.context = context;
        this.bookingsList = bookingsList;
        this.roomsList = roomsList;
        this.date = date;
        booking_controller = new Booking_controller(bookingsList);
    }

    @NonNull
    @Override
    public BookingAdapter.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.booking_tile, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position) {
        Room room = roomsList.get(position);
        holder.room_title.setText(room.getTitle());

        Pair<Boolean, Booking> pair = booking_controller.isRoomBookedOn(date, room);

        if(pair.getFirst()) {
            holder.room_status.setText("Booked");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Booking_info_Activity.class);
                    intent.putExtra("booking", pair.getSecond());
                    context.startActivity(intent);
                }
            });
        } else {
            holder.room_status.setText("Available");
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

    @Override
    public int getItemCount() {
        return roomsList.size();
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder {

        TextView room_title, room_status;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            room_title = itemView.findViewById(R.id.room_title);
            room_status = itemView.findViewById(R.id.room_status);
        }
    }

    public void updateDate(LocalDate date) {
        this.date = date;
        notifyDataSetChanged();
    }

}

