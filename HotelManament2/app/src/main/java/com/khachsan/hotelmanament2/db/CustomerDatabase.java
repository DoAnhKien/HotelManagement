package com.khachsan.hotelmanament2.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.khachsan.hotelmanament2.model.Customer;


@Database(entities = {Customer.class}, version = 3, exportSchema = false)
public abstract class CustomerDatabase extends RoomDatabase {
    public abstract CustomerDao customerDao();
}
