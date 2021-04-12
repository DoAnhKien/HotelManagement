package com.example.hotelmanament2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.hotelmanament2.model.HotelRoom;

import java.util.List;

@Dao
public interface HotelRoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHotelRoom(HotelRoom hotelRoom);

    @Update
    void updateHotelRoom(HotelRoom hotelRoom);

    @Delete
    void deleteHotelRoom(HotelRoom hotelRoom);

    @Query("SELECT * FROM hotel_room_table")
    LiveData<List<HotelRoom>> getAllHotelRoom();
}
