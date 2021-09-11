package com.khachsan.hotelmanament2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "hotel_room_table")
public class HotelRoom implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;
    @ColumnInfo(name = "_roomNumber")
    private int roomNumber;
    @ColumnInfo(name = "_typeRoom")
    private String typeRoom;
    @ColumnInfo(name = "_status")
    private String status;
    @ColumnInfo(name = "_numberCustomer")
    private int numberCustomer;
    @ColumnInfo(name = "_dayPrice")
    private int dayPrice;
    @ColumnInfo(name = "_firstHourPrice")
    private int fristHourPrice;
    @ColumnInfo(name = "_hourPrices")
    private int hourPrice;


    public HotelRoom(int roomNumber, String typeRoom, String status, int numberCustomer, int dayPrice, int fristHourPrice, int hourPrice) {
        this.roomNumber = roomNumber;
        this.typeRoom = typeRoom;
        this.status = status;
        this.numberCustomer = numberCustomer;
        this.dayPrice = dayPrice;
        this.fristHourPrice = fristHourPrice;
        this.hourPrice = hourPrice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberCustomer() {
        return numberCustomer;
    }

    public void setNumberCustomer(int numberCustomer) {
        this.numberCustomer = numberCustomer;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(int dayPrice) {
        this.dayPrice = dayPrice;
    }

    public int getFristHourPrice() {
        return fristHourPrice;
    }

    public void setFristHourPrice(int fristHourPrice) {
        this.fristHourPrice = fristHourPrice;
    }

    public int getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(int hourPrice) {
        this.hourPrice = hourPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelRoom hotelRoom = (HotelRoom) o;
        return id == hotelRoom.id &&
                roomNumber == hotelRoom.roomNumber &&
                numberCustomer == hotelRoom.numberCustomer &&
                dayPrice == hotelRoom.dayPrice &&
                fristHourPrice == hotelRoom.fristHourPrice &&
                hourPrice == hotelRoom.hourPrice &&
                typeRoom.equals(hotelRoom.typeRoom) &&
                status.equals(hotelRoom.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, typeRoom, status, numberCustomer, dayPrice, fristHourPrice, hourPrice);
    }
}
