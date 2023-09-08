package com.example.myapplication.Controllers;

import android.widget.Toast;

import com.example.myapplication.Booking_info_Activity;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;

import java.time.LocalDate;
import java.util.ArrayList;

import kotlin.Pair;

public class Booking_controller {

    ArrayList<Booking> bookingsList;

    public Booking_controller( ArrayList<Booking> bookingsList) {
        this.bookingsList = bookingsList;
    }

    public ArrayList<Booking> bookingsOn(LocalDate date){
        // Returns a list of bookings on a given date
        try {
            ArrayList<Booking> bookingsOnDate = new ArrayList<>();
            for(Booking booking : bookingsList){
                if(booking.getBookedDates().contains(date)){
                    bookingsOnDate.add(booking);
                }
            }
            return bookingsOnDate;
        }
        catch (Exception e){
            Toast.makeText(null, "Error in bookingsOn", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public Pair<Boolean, Booking> isRoomBookedOn(LocalDate date, Room room) {
        // Returns true if a room is booked on a given date
        try {
            ArrayList<Booking> bookingsOnDate = bookingsOn(date);
            for (Booking booking : bookingsOnDate) {
                if (booking.getRoomTitle().equals(room.getTitle())) {
                    return new Pair<>(true, booking);
                }
            }
            return new Pair<>(false, null);

        }
        catch (Exception e){
            Toast.makeText(null, "Error in isRoomBookedOn", Toast.LENGTH_SHORT).show();
            return null;
        }

    }
}
