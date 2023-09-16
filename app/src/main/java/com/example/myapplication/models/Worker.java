package com.example.myapplication.models;

public class Worker {
    private String name, staffType;

    public Worker() {
    }

    public Worker(String name, String staffType) {
        this.name = name;
        this.staffType = staffType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }
}
