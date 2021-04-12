package com.example.hotelmanament2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.model.Service;

@Database(entities = {Service.class}, version = 1, exportSchema = false)
public abstract class ServicesDatabase extends RoomDatabase {
    public abstract ServicesDao servicesDao();
}
