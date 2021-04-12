package com.example.hotelmanagerment.model;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer {
    @Id()
    private int idCustomer;
    private String customerName;
    private String identityCard;
    private String dateOfBirth;
    private int roomNumber;
    private int avatarCustomer;

    public Customer(String customerName, String identityCard, String dateOfBirth, int roomNumber, int avatarCustomer) {
        this.customerName = customerName;
        this.identityCard = identityCard;
        this.dateOfBirth = dateOfBirth;
        this.roomNumber = roomNumber;
        this.avatarCustomer = avatarCustomer;
    }

    public Customer() {

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
