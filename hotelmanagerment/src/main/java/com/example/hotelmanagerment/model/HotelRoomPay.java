package com.example.hotelmanagerment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "hotel_room_pay")
public class HotelRoomPay {
    @Id()
    private int hotelRoomNumber;
    private String content;
    private long totalMoney;

    public HotelRoomPay() {
    }

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
