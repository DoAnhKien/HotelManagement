package com.khachsan.hotelmanament2.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.khachsan.hotelmanament2.model.HotelRoom;


@Database(entities = {HotelRoom.class}, version = 4, exportSchema = false)
public abstract class HotelRoomDatabase extends RoomDatabase {
    public static HotelRoomDatabase INSTANCES;

    public abstract HotelRoomDao hotelRoomDao();


}
