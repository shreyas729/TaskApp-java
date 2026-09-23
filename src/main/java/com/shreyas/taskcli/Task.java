package com.shreyas.taskcli;

import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;

    public Task(int id, String title, String description, TaskStatus status, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    public Task(String title, String description, TaskStatus status, LocalDate dueDate) {
        this( 0 , title, description , status, dueDate);
    }
    public Task(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    @Override
    public String toString(){
        return "Task{" + "id="+id +", title=" + title +"}";
    }
}

