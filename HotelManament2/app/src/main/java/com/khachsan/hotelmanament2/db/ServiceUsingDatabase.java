package com.khachsan.hotelmanament2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.khachsan.hotelmanament2.model.ServiceUsing;

@Database(entities = {ServiceUsing.class}, version = 1, exportSchema = false)
public abstract class ServiceUsingDatabase extends RoomDatabase {
    public abstract ServiceUsingDao serviceUsingDao();
}
