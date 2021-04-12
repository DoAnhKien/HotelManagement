package com.example.hotelmanament2.di;

import android.app.Application;

import androidx.room.Room;

import com.example.hotelmanament2.db.CustomerDao;
import com.example.hotelmanament2.db.CustomerDatabase;
import com.example.hotelmanament2.db.DateTimeDao;
import com.example.hotelmanament2.db.DateTimeDatabase;
import com.example.hotelmanament2.model.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class CustomerModule {
    @Provides
    @Singleton
    public static CustomerDatabase providesCustomerDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(),
                CustomerDatabase.class, "customer_hotel_database.db")
                .fallbackToDestructiveMigration()
                .build();

    }

    @Singleton
    @Provides
    public static CustomerDao providesCustomerDao(CustomerDatabase database) {
        return database.customerDao();
    }
}
