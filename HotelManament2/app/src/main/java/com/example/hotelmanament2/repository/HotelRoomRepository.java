package com.example.hotelmanament2.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.hotelmanament2.db.HotelRoomDao;
import com.example.hotelmanament2.db.HotelRoomDatabase;
import com.example.hotelmanament2.model.HotelRoom;

import java.util.List;

import javax.inject.Inject;

public class HotelRoomRepository {
    private HotelRoomDao hotelRoomDao;
    private LiveData<List<HotelRoom>> allHotelRoom;

    @Inject
    public HotelRoomRepository(HotelRoomDao hotelRoomDao) {
        this.hotelRoomDao = hotelRoomDao;
        this.allHotelRoom = hotelRoomDao.getAllHotelRoom();
    }

    public void insertHotelRoom(HotelRoom hotelRoom) {
        new InsertHotelRoomTask(hotelRoomDao).execute(hotelRoom);
    }

    public void updateHotelRoom(HotelRoom hotelRoom) {
        new UpdateHotelRoomTask(hotelRoomDao).execute(hotelRoom);
    }

    public void deleteHotelRoom(HotelRoom hotelRoom) {
        new DeleteHotelRoomTask(hotelRoomDao).execute(hotelRoom);
    }

    public LiveData<List<HotelRoom>> getAllHotelRoom() {
        return allHotelRoom;
    }

    private class InsertHotelRoomTask extends AsyncTask<HotelRoom, Void, Void> {
        private HotelRoomDao hotelRoomDao;

        public InsertHotelRoomTask(HotelRoomDao hotelRoomDao) {
            this.hotelRoomDao = hotelRoomDao;
        }

        @Override
        protected Void doInBackground(HotelRoom... hotelRooms) {
            hotelRoomDao.insertHotelRoom(hotelRooms[0]);
            return null;
        }
    }

    private class UpdateHotelRoomTask extends AsyncTask<HotelRoom, Void, Void> {
        private HotelRoomDao hotelRoomDao;

        public UpdateHotelRoomTask(HotelRoomDao hotelRoomDao) {
            this.hotelRoomDao = hotelRoomDao;
        }

        @Override
        protected Void doInBackground(HotelRoom... hotelRooms) {
            hotelRoomDao.updateHotelRoom(hotelRooms[0]);
            return null;
        }
    }

    private class DeleteHotelRoomTask extends AsyncTask<HotelRoom, Void, Void> {
        private HotelRoomDao hotelRoomDao;

        public DeleteHotelRoomTask(HotelRoomDao hotelRoomDao) {
            this.hotelRoomDao = hotelRoomDao;
        }

        @Override
        protected Void doInBackground(HotelRoom... hotelRooms) {
            hotelRoomDao.deleteHotelRoom(hotelRooms[0]);
            return null;
        }
    }

}
