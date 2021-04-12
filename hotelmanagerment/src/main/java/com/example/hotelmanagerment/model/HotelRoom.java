package com.example.hotelmanagerment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "hotel_room")
public class HotelRoom {
    @Id()
    private int id;
    private int roomNumber;
    private String typeRoom;
    private String status;
    private int numberCustomer;
    private int dayPrice;
    private int fristHourPrice;
    private int hourPrice;

    public HotelRoom() {
    }

    public HotelRoom(int id, int roomNumber, String typeRoom, String status, int numberCustomer, int dayPrice, int fristHourPrice, int hourPrice) {
        this.id = id;
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
