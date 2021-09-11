package com.khachsan.hotelmanament2.callback;

import com.khachsan.hotelmanament2.model.Customer;

public interface MyCustomerOnClicked {
    void handleMyClicked(int posstion, Customer customer);

    void handleMyCustomerOnLongClieck(int position, Customer customer);
}
