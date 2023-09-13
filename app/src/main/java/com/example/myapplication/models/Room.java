package com.example.myapplication.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
    private String  status, description, documentId,title;

    boolean isAC;
    private long pricePerNight;
    private int maxGuests,numBaths,numBeds;

    ArrayList<String> offers;



    public Room() {
    }

    public Room( String title, String status, String description, long pricePerNight, int numBaths, int numBeds, int maxGuests, boolean isAC, ArrayList<String> offers) {
        this.title = title;
        this.isAC = isAC;
        this.maxGuests = maxGuests;
        this.numBeds = numBeds;
        this.offers = offers;
        this.status = status;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.numBaths = numBaths;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsAC() {
        return isAC;
    }

    public void setIsAC(boolean AC) {
        isAC = AC;
    }

    public long getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(long pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public int getNumBaths() {
        return numBaths;
    }

    public void setNumBaths(int numBaths) {
        this.numBaths = numBaths;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    public ArrayList<String> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<String> offers) {
        this.offers = offers;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String OffersToString() {
        String offers = "";
        for (String offer : this.offers) {
            offers += offer + ", ";
        }
        return offers.substring(0, offers.length() - 2);
    }

}
