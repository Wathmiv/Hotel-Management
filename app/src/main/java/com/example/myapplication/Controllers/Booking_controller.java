package com.example.myapplication.Controllers;

import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import kotlin.Pair;

public class Booking_controller {

    ArrayList<Booking> bookingsList;

    public Booking_controller(ArrayList<Booking> bookingsList) {
        this.bookingsList = bookingsList;
    }

    public ArrayList<Booking> bookingsOn(ArrayList<LocalDate> date) {
        // Returns a list of bookings on a given list of dates
        ArrayList<Booking> bookingsOnDate = new ArrayList<>();
        for (LocalDate d : date) {
            for (Booking booking : bookingsList) {
                if (booking.getBookedDates().contains(d)) {
                    bookingsOnDate.add(booking);
                    break;
                }
            }
        }
        return bookingsOnDate;
    }

    public Pair<Boolean, Booking> isRoomBookedOn(ArrayList<LocalDate> date, Room room) {
        // Returns true if a room is booked on a given list of dates
        ArrayList<Booking> bookingsOnDate = bookingsOn(date);
        for (Booking booking : bookingsOnDate) {
            if (booking.getRoomTitle().equals(room.getTitle())) {
                return new Pair<>(true, booking);
            }
        }
        return new Pair<>(false, null);
    }

    // Helper method to convert LocalDateTime list to LocalDate list
    private ArrayList<LocalDate> convertLocalDateTimeToLocalDate(ArrayList<LocalDateTime> dateTimeList) {
        ArrayList<LocalDate> dateList = new ArrayList<>();
        for (LocalDateTime dateTime : dateTimeList) {
            dateList.add(dateTime.toLocalDate());
        }
        return dateList;
    }

    // Format LocalDateTime to a string
    private String getFormattedDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
