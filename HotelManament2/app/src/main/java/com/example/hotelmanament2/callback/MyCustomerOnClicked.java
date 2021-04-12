package com.example.hotelmanament2.callback;

import com.example.hotelmanament2.model.Customer;

public interface MyCustomerOnClicked {
    void handleMyClicked(int posstion, Customer customer);

    void handleMyCustomerOnLongClieck(int position, Customer customer);
}
