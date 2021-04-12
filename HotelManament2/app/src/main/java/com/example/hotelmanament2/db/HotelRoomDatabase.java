package com.example.hotelmanament2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hotelmanament2.model.HotelRoom;


@Database(entities = {HotelRoom.class}, version = 4, exportSchema = false)
public abstract class HotelRoomDatabase extends RoomDatabase {
    public static HotelRoomDatabase INSTANCES;

    public abstract HotelRoomDao hotelRoomDao();


}
