package com.khachsan.hotelmanament2.di;

import android.app.Application;

import androidx.room.Room;


import com.khachsan.hotelmanament2.db.HotelRoomDao;
import com.khachsan.hotelmanament2.db.HotelRoomDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class HotelRoomModule {

    @Provides
    public static HotelRoomDatabase providesHotelRoomDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(),
                HotelRoomDatabase.class, "hotel_room_database.db")
                .fallbackToDestructiveMigration()
                .build();
    }


    @Provides
    public static HotelRoomDao providesHotelRoomDao(HotelRoomDatabase database) {
        return database.hotelRoomDao();
    }
}
