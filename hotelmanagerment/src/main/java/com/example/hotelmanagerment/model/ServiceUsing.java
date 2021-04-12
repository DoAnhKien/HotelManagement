package com.example.hotelmanagerment.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "service_using")
public class ServiceUsing {

    @Id()
    private int idServices;
    private String nameServices;
    private String typeCount;
    private int pricesServices;
    private String statusServices;
    private int hotelRoomNumber;

    public ServiceUsing() {
    }

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

