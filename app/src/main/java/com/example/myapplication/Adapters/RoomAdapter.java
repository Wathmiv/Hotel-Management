package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Room_info_Activity;
import com.example.myapplication.models.Room;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{

    Context context;
    ArrayList<Room> roomsList;

public RoomAdapter(Context context, ArrayList<Room> roomsList) {
        this.context = context;
        this.roomsList = roomsList;
    }

    @NonNull
    @Override
    public RoomAdapter.RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.room_tile, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.RoomViewHolder holder, int position) {

            Room room = roomsList.get(position);
            holder.room_title.setText(room.getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Room_info_Activity.class);
                    intent.putExtra("room",room);

                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return roomsList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{

        TextView room_title;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            room_title = itemView.findViewById(R.id.room_title);


        }
    }
}
