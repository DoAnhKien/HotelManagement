package com.example.hotelmanament2.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hotelmanament2.db.HotelRoomDao;
import com.example.hotelmanament2.db.HotelRoomPayDao;
import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.model.HotelRoomPay;

import java.util.List;

import javax.inject.Inject;

public class HotelRoomPayRepository {

    public HotelRoomPayDao hotelRoomPayDao;

    @Inject
    public HotelRoomPayRepository(HotelRoomPayDao hotelRoomPayDao) {
        this.hotelRoomPayDao = hotelRoomPayDao;
    }

    public void insertHotelRoomPay(HotelRoomPay hotelRoomPay) {
        new InsertHotelRoomPayTask(hotelRoomPayDao).execute(hotelRoomPay);
    }

    public void deletehotelHotelRoomPay(HotelRoomPay hotelRoomPay) {
        new DeleteHotelRoomPayTask(hotelRoomPayDao).execute(hotelRoomPay);
    }

    public LiveData<List<HotelRoomPay>> getAllHotelRoomPay() {
        return hotelRoomPayDao.getAllHotelRoomPay();
    }

    public LiveData<HotelRoomPay> getHotelRoomPayByRoomNumber(int hotelRoomNumber) {
        return hotelRoomPayDao.getHotelRoomPayByHotelRoomNumber(hotelRoomNumber);
    }

    private class InsertHotelRoomPayTask extends AsyncTask<HotelRoomPay, Void, Void> {
        private HotelRoomPayDao hotelRoomPayDao;

        public InsertHotelRoomPayTask(HotelRoomPayDao hotelRoomPayDao) {
            this.hotelRoomPayDao = hotelRoomPayDao;
        }

        @Override
        protected Void doInBackground(HotelRoomPay... hotelRoomPays) {
            hotelRoomPayDao.insertHotelRoomPay(hotelRoomPays[0]);
            return null;
        }
    }

    private class DeleteHotelRoomPayTask extends AsyncTask<HotelRoomPay, Void, Void> {
        private HotelRoomPayDao hotelRoomPayDao;

        public DeleteHotelRoomPayTask(HotelRoomPayDao hotelRoomPayDao) {
            this.hotelRoomPayDao = hotelRoomPayDao;
        }

        @Override
        protected Void doInBackground(HotelRoomPay... hotelRoomPays) {
            hotelRoomPayDao.deleteHotelRoomPay(hotelRoomPays[0]);
            return null;
        }
    }
}
