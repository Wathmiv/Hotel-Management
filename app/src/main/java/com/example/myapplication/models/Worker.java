package com.example.myapplication.models;

public class Worker {
    private String name, staffType;
    private Integer id;

    public Worker() {
    }

    public Worker(String name, String staffType, Integer id) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
