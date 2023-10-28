package com.example.myapplication.Adapters;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;

import java.util.List;
import java.util.Objects;

public class Room_Images_Adapter extends RecyclerView.Adapter<Room_Images_Adapter.Room_Images_ViewHolder> {

    private List<String> images;
    private Context context;
    LayoutInflater mLayoutInflater;

    public Room_Images_Adapter(List<String> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public Room_Images_Adapter.Room_Images_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.room_image, parent, false);
        return new Room_Images_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Room_Images_Adapter.Room_Images_ViewHolder holder, int position) {
        String image = images.get(position);
        Glide.with(context).load(image).into(holder.room_image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class Room_Images_ViewHolder extends RecyclerView.ViewHolder{
        ImageView room_image;
        public Room_Images_ViewHolder(@NonNull View itemView) {
            super(itemView);
            room_image = itemView.findViewById(R.id.roomImage);
        }
    }

}
