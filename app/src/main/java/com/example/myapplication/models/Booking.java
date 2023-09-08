package com.example.myapplication.models;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class Booking implements Serializable {

    String documentId, roomTitle, bookingStartDate, bookingEndDate;
    Map<String,String> bookedBy;
    Integer numberOfGuests;
    long price;

    public Booking() {
    }

    public Booking( String roomTitle, String bookingStartDate,
                   String bookingEndDate, Integer numberOfGuests, long price, Map<String, String> bookedBy) {
        this.roomTitle = roomTitle;
        this.bookingStartDate = bookingStartDate;
        this.bookingEndDate = bookingEndDate;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.bookedBy = bookedBy;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(String bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public String getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingEndDate(String bookingEndDate) {
        this.bookingEndDate = bookingEndDate;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Map<String, String> getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(Map<String, String> bookedBy) {
        this.bookedBy = bookedBy;
    }
    public ArrayList<LocalDate> getBookedDates() {
        // Convert the booking start and end dates to LocalDate objects
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d, yyyy, h:mm:ss a", Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(bookingStartDate, formatter);
        LocalDate endDate = LocalDate.parse(bookingEndDate, formatter);
        // Create an ArrayList of LocalDate objects representing the dates between the start and end dates,
        ArrayList<LocalDate> bookedDates = new ArrayList<>();
        while (!startDate.isAfter(endDate.minusDays(1))) {
            bookedDates.add(startDate);
            startDate = startDate.plusDays(1);
        }

        return bookedDates;
    }

    public int getTotalNights(){
        ArrayList<LocalDate> bookedDates = getBookedDates();
        return bookedDates.size();
    }
}
