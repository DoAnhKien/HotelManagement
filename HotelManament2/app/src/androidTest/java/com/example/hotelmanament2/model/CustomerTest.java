package com.example.hotelmanament2.model;

import static org.junit.jupiter.api.Assertions.*;

import com.khachsan.hotelmanament2.model.Customer;

class CustomerTest {

    @org.junit.jupiter.api.Test
    void getIdCustomer() {
        Customer customer = new Customer("kienda", "kienda", "kienda", 123, 123);
        assertEquals("kienda",customer.getCustomerName());
    }
}