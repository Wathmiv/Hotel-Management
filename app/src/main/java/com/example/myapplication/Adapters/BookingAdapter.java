package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    Context context;
    ArrayList<Booking> bookingsList;
    ArrayList<Room> roomsList;

    public BookingAdapter(Context context, ArrayList<Booking> bookingsList, ArrayList<Room> roomsList) {
        this.context = context;
        this.bookingsList = bookingsList;
        this.roomsList = roomsList;
    }

    @NonNull
    @Override
    public BookingAdapter.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_tile, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public abstract void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position);

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


    public String getFormattedDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy, hh:mm:ss a");
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }
}
