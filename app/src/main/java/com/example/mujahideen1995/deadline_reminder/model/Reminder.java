package com.example.mujahideen1995.deadline_reminder.model;

/**
 * Created by mujahideen1995 on 10/11/17.
 */

public class Reminder {



    private int id;
    private String title;
    private String description;
    private String date;
    private String time;

    public Reminder() {
    }

    public Reminder(String title, String description, String date, String time) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }

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

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public String getDate(){
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime(){
        return description;
    }
    public void setTime(String time){
        this.time = time;
    }


}
