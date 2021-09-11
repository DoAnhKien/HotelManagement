package com.khachsan.hotelmanament2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.khachsan.hotelmanament2.model.HotelRoomPay;

@Database(entities = {HotelRoomPay.class}, version = 1, exportSchema = false)
public abstract class HotelRoomPayDatabase extends RoomDatabase {

    public abstract HotelRoomPayDao hotelRoomPayDao();


}
