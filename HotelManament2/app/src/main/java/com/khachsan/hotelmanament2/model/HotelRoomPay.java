package com.khachsan.hotelmanament2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "hotel_room_pay_table")
public class HotelRoomPay implements Serializable {
    @PrimaryKey
    private int hotelRoomNumber;
    private String content;
    private long totalMoney;

    public HotelRoomPay(int hotelRoomNumber, String content, long totalMoney) {
        this.hotelRoomNumber = hotelRoomNumber;
        this.content = content;
        this.totalMoney = totalMoney;
    }

    public int getHotelRoomNumber() {
        return hotelRoomNumber;
    }

    public void setHotelRoomNumber(int hotelRoomNumber) {
        this.hotelRoomNumber = hotelRoomNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }
}
