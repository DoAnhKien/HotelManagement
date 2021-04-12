package com.example.hotelmanament2.di;

import android.app.Application;

import androidx.room.Room;

import com.example.hotelmanament2.db.ServiceUsingDao;
import com.example.hotelmanament2.db.ServiceUsingDatabase;
import com.example.hotelmanament2.db.ServicesDao;
import com.example.hotelmanament2.db.ServicesDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class ServiceUsingModule {
    @Provides
    public static ServiceUsingDatabase providesServiceUsingsDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(),
                ServiceUsingDatabase.class, "service_using_database.db")
                .fallbackToDestructiveMigration()
                .build();
    }


    @Provides
    public static ServiceUsingDao providesServiceUsingDao(ServiceUsingDatabase database) {
        return database.serviceUsingDao();
    }
}
