package com.example.hotelmanament2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hotelmanament2.model.Service;
import com.example.hotelmanament2.model.ServiceUsing;

import java.util.List;

@Dao
public interface ServiceUsingDao {

    @Insert
    void insertServiceUsing(ServiceUsing serviceUsing);

    @Delete
    void deleteServiceUsing(ServiceUsing serviceUsing);

    @Update
    void updateServiceUsing(ServiceUsing serviceUsing);

    @Query("SELECT * FROM service_using_table WHERE _horelRoomNumber= :hotelRoomNumber")
    LiveData<List<ServiceUsing>> getAllServiceUsingByHotelRoomNumber(int hotelRoomNumber);
}