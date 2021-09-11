package com.khachsan.hotelmanament2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.khachsan.hotelmanament2.model.HotelRoomPay;

import java.util.List;

@Dao
public interface HotelRoomPayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHotelRoomPay(HotelRoomPay hotelRoomPay);

    @Delete
    void deleteHotelRoomPay(HotelRoomPay hotelRoomPay);

    @Query("SELECT * FROM hotel_room_pay_table")
    LiveData<List<HotelRoomPay>> getAllHotelRoomPay();

    @Query("SELECT * FROM hotel_room_pay_table WHERE hotelRoomNumber = :hotelRoomNumber")
    LiveData<HotelRoomPay> getHotelRoomPayByHotelRoomNumber(int hotelRoomNumber);
}
