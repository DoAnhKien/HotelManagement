package com.example.hotelmanament2.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void getIdCustomer() {

    }

    @Test
    void getCustomerName() {
        Customer customer = new Customer("kienda", "kien", "kien", 123, 4);
        assertEquals("kienda", customer.getCustomerName());
    }

    @Test
    void getDateOfBirth(){
        Customer customer = new Customer("kienda", "kien", "kien", 123, 4);
        assertEquals("kien", customer.getDateOfBirth());
    }
}