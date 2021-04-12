package com.example.hotelmanament2.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hotelmanament2.model.Service;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface ServicesDao {

    @Insert
    void addServices(Service service);

    @Update
    void updateServices(Service service);

    @Delete
    void deleteServices(Service service);

    @Query("SELECT * FROM service_table")
    LiveData<List<Service>> getAllServices();
}
