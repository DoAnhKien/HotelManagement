package com.khachsan.hotelmanament2.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.khachsan.hotelmanament2.model.Service;

@Database(entities = {Service.class}, version = 1, exportSchema = false)
public abstract class ServicesDatabase extends RoomDatabase {
    public abstract ServicesDao servicesDao();
}
