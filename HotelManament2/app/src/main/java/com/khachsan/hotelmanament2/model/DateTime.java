package com.khachsan.hotelmanament2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "date_time_table")
public class DateTime implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;
    @ColumnInfo(name = "_roomNumber")
    private int roomNumber;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "_hour")
    private String hour;
    @ColumnInfo(name = "_count_time")
    private long countTime;

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
