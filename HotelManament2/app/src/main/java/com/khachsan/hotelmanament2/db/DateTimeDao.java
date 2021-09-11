package com.khachsan.hotelmanament2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.khachsan.hotelmanament2.model.DateTime;

import java.util.List;

@Dao
public interface DateTimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDateTime(DateTime dateTime);

    @Update
    void upDatetDateTime(DateTime dateTime);

    @Delete
    void deleteDateTime(DateTime dateTime);

    @Query("SELECT * FROM date_time_table WHERE _roomNumber = :roomNumber")
    LiveData<List<DateTime>> getAllDateTimeByRoomNumber(int roomNumber);


}
