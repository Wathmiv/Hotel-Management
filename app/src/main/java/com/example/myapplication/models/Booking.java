package com.example.myapplication.models;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class Booking implements Serializable {

    String documentId, roomTitle;
    LocalDate startDate, endDate;
    Date bookingStartDate, bookingEndDate;
    Map<String,String> bookedBy;
    Integer numberOfGuests,roomNum;
    long price;

    public Booking() {
    }

    public Booking( String roomTitle, Date bookingStartDate,
                    Date bookingEndDate, Integer numberOfGuests, long price, Map<String, String> bookedBy, Integer roomNum) {
        this.roomTitle = roomTitle;
        this.bookingStartDate =  bookingStartDate;
        this.bookingEndDate = bookingEndDate;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.bookedBy = bookedBy;
        this.roomNum = roomNum;
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

    public Date getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(Date bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public Date getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingEndDate(Date bookingEndDate) {
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

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }
    public ArrayList<LocalDate> getBookedDates() {

        startDate = bookingStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        endDate = bookingEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

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
