package com.khachsan.hotelmanament2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "service_using_table")
public class ServiceUsing implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_idServices")
    private int idServices;
    @ColumnInfo(name = "_nameServices")
    private String nameServices;
    @ColumnInfo(name = "_typeCount")
    private String typeCount;
    @ColumnInfo(name = "_pricesServices")
    private int pricesServices;
    @ColumnInfo(name = "_statusServices")
    private String statusServices;
    @ColumnInfo(name = "_horelRoomNumber")
    private int hotelRoomNumber;

    public ServiceUsing(String nameServices, String typeCount, int pricesServices, String statusServices, int hotelRoomNumber) {
        this.nameServices = nameServices;
        this.typeCount = typeCount;
        this.pricesServices = pricesServices;
        this.statusServices = statusServices;
        this.hotelRoomNumber = hotelRoomNumber;
    }

    public int getHotelRoomNumber() {
        return hotelRoomNumber;
    }

    public void setHotelRoomNumber(int hotelRoomNumber) {
        this.hotelRoomNumber = hotelRoomNumber;
    }

    public int getIdServices() {
        return idServices;
    }

    public void setIdServices(int idServices) {
        this.idServices = idServices;
    }

    public String getNameServices() {
        return nameServices;
    }

    public void setNameServices(String nameServices) {
        this.nameServices = nameServices;
    }

    public int getPricesServices() {
        return pricesServices;
    }

    public void setPricesServices(int pricesServices) {
        this.pricesServices = pricesServices;
    }

    public String getStatusServices() {
        return statusServices;
    }

    public void setStatusServices(String statusServices) {
        this.statusServices = statusServices;
    }

    public String getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(String typeCount) {
        this.typeCount = typeCount;
    }
}

