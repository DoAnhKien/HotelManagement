package com.khachsan.hotelmanament2.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.khachsan.hotelmanament2.model.DateTime;


@Database(entities = {DateTime.class}, version = 2, exportSchema = false)
public abstract class DateTimeDatabase extends RoomDatabase {

    public static DateTimeDatabase INSTANCES;

    public abstract DateTimeDao dateTimeDao();


}
