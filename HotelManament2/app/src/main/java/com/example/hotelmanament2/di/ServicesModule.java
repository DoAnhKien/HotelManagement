package com.example.hotelmanament2.di;

import android.app.Application;
import android.app.Service;

import androidx.room.Room;

import com.example.hotelmanament2.db.HotelRoomDao;
import com.example.hotelmanament2.db.HotelRoomDatabase;
import com.example.hotelmanament2.db.ServicesDao;
import com.example.hotelmanament2.db.ServicesDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class ServicesModule {
    @Provides
    public static ServicesDatabase providesServicesDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(),
                ServicesDatabase.class, "service_database.db")
                .fallbackToDestructiveMigration()
                .build();
    }


    @Provides
    public static ServicesDao providesServicesDao(ServicesDatabase database) {
        return database.servicesDao();
    }
}
