package com.example.hotelmanagerment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "date_time")
public class DateTime {
    @Id()
    private int id;
    private int roomNumber;
    private String date;
    private String hour;
    private long countTime;

    public DateTime() {
    }

    public DateTime(int roomNumber, String date, String hour, long countTime) {
        this.roomNumber = roomNumber;
        this.date = date;
        this.hour = hour;
        this.countTime = countTime;
    }

    public long getCountTime() {
        return countTime;
    }

    public void setCountTime(long countTime) {
        this.countTime = countTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
