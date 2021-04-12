package com.example.hotelmanament2.di;

import android.app.Application;

import androidx.room.Room;

import com.example.hotelmanament2.db.HotelRoomDao;
import com.example.hotelmanament2.db.HotelRoomDatabase;
import com.example.hotelmanament2.db.HotelRoomPayDao;
import com.example.hotelmanament2.db.HotelRoomPayDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class HotelRoomPayModule {
    @Provides
    @Singleton
    public static HotelRoomPayDatabase providesHotelRoomPayDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(),
                HotelRoomPayDatabase.class, "hotel_room_pay_database.db")
                .fallbackToDestructiveMigration()
                .build();
    }


    @Provides
    @Singleton
    public static HotelRoomPayDao providesHotelRoomPayDao(HotelRoomPayDatabase database) {
        return database.hotelRoomPayDao();
    }
}
