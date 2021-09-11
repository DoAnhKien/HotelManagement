package com.khachsan.hotelmanament2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.khachsan.hotelmanament2.model.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCustomer(Customer customer);

    @Update
    void updateCustomer(Customer customer);

    @Delete
    void deleteCustomer(Customer customer);

    @Query("SELECT * FROM customer_table where _roomNumber = :roomNumber")
    LiveData<List<Customer>> getAllCustomer(int roomNumber);

    @Query("SELECT * FROM customer_table")
    LiveData<List<Customer>> getAllCustomerUsing();
}
