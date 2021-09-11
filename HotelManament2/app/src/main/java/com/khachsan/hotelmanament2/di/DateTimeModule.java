package com.khachsan.hotelmanament2.di;


import android.app.Application;

import androidx.room.Room;

import com.khachsan.hotelmanament2.db.DateTimeDao;
import com.khachsan.hotelmanament2.db.DateTimeDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DateTimeModule {
    @Provides
    @Singleton
    public static DateTimeDatabase providesDateTimeDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(),
                DateTimeDatabase.class, "date_time_database.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public static DateTimeDao providesDateTimeDao(DateTimeDatabase database) {
        return database.dateTimeDao();
    }
}
