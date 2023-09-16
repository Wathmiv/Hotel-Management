package com.example.myapplication.models;

public class Task {
    String taskName, Description, assignedTo;


    public Task() {
    }

    public Task(String taskName, String description, String assignedTo) {
        this.taskName = taskName;
        Description = description;
        this.assignedTo = assignedTo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }



}
