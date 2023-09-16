package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Booked_Rooms_Activity;
import com.example.myapplication.Booking_info_Activity;
import com.example.myapplication.R;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booked_Room_Adapter extends RecyclerView.Adapter<Booked_Room_Adapter.Booked_Room_ViewHolder> {

    Context context;
    ArrayList<Booking> bookingsList;
    LocalDate date;
    Room room;

    public Booked_Room_Adapter(Context context, ArrayList<Booking> bookingsList, LocalDate date, Room room) {
        this.context = context;
        this.bookingsList = bookingsList;
        this.date = date;
        this.room = room;
    }
    @NonNull
    @Override
    public Booked_Room_Adapter.Booked_Room_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booked_room_tile, parent, false);
        return new Booked_Room_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Booked_Room_Adapter.Booked_Room_ViewHolder holder, int position) {
        Booking booking = bookingsList.get(position);
        holder.roomNum.setText(String.valueOf(booking.getRoomNum()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Booking_info_Activity.class);
                intent.putExtra("booking", booking);
                intent.putExtra("room", room);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return bookingsList.size();
    }

    public class Booked_Room_ViewHolder extends RecyclerView.ViewHolder {
        TextView roomNum;
        public Booked_Room_ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomNum = itemView.findViewById(R.id.roomNum);
        }
    }
}
