package com.example.hotelmanament2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "customer_table")
public class Customer implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_idCustomer")
    private int idCustomer;
    @ColumnInfo(name = "_customerName")
    private String customerName;
    @ColumnInfo(name = "_identityCard")
    private String identityCard;
    @ColumnInfo(name = "_dateOfBirth")
    private String dateOfBirth;
    @ColumnInfo(name = "_roomNumber")
    private int roomNumber;
    @ColumnInfo(name = "_avatarCustomer")
    private int avatarCustomer;

    public Customer(String customerName, String identityCard, String dateOfBirth, int roomNumber, int avatarCustomer) {
        this.customerName = customerName;
        this.identityCard = identityCard;
        this.dateOfBirth = dateOfBirth;
        this.roomNumber = roomNumber;
        this.avatarCustomer = avatarCustomer;
    }


    public int getAvatarCustomer() {
        return avatarCustomer;
    }

    public void setAvatarCustomer(int avatarCustomer) {
        this.avatarCustomer = avatarCustomer;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
