package com.example.myapplication.models;

public class Task {
    String taskName, Description, assignedTo, status, completeBefore;


    public Task() {
    }

    public Task(String taskName, String description, String assignedTo, String status) {
        this.taskName = taskName;
        Description = description;
        this.assignedTo = assignedTo;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompleteBefore() {
        return completeBefore;
    }

    public void setCompleteBefore(String completeBefore) {
        this.completeBefore = completeBefore;
    }
}
