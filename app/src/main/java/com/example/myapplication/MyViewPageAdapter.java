package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.fragments.Bookings_fragment;
import com.example.myapplication.fragments.Room_fragment;
import com.example.myapplication.fragments.Staff_fragment;
import com.example.myapplication.fragments.Task_fragment;

public class MyViewPageAdapter extends FragmentStateAdapter {
    public MyViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new Room_fragment();
            case 1: return new Bookings_fragment();
            case 2: return new Staff_fragment();
            case 3: return new Task_fragment();
            default: return new Room_fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
