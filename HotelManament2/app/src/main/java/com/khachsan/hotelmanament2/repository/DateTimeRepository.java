package com.khachsan.hotelmanament2.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.khachsan.hotelmanament2.db.DateTimeDao;
import com.khachsan.hotelmanament2.model.DateTime;

import java.util.List;

import javax.inject.Inject;

public class DateTimeRepository {
    public DateTimeDao dateTimeDao;

    @Inject
    public DateTimeRepository(DateTimeDao dateTimeDao) {
        this.dateTimeDao = dateTimeDao;
    }

    public void insertDateTime(DateTime dateTime) {
        new InsertDateTimeTask(dateTimeDao).execute(dateTime);
    }

    public void updateDateTime(DateTime dateTime) {
        new UpDateDateTimeTask(dateTimeDao).execute(dateTime);
    }

    public void deleteDateTime(DateTime dateTime) {
        new DeleteDateTimeTask(dateTimeDao).execute(dateTime);
    }

    public LiveData<List<DateTime>> getAllDateTimeByRoomNumber(int roomNumber) {
        return dateTimeDao.getAllDateTimeByRoomNumber(roomNumber);
    }

    public class InsertDateTimeTask extends AsyncTask<DateTime, Void, Void> {
        private DateTimeDao dateTimeDao;

        public InsertDateTimeTask(DateTimeDao dateTimeDao) {
            this.dateTimeDao = dateTimeDao;
        }

        @Override
        protected Void doInBackground(DateTime... dateTimes) {
            dateTimeDao.insertDateTime(dateTimes[0]);
            return null;
        }
    }

    public class UpDateDateTimeTask extends AsyncTask<DateTime, Void, Void> {
        private DateTimeDao dateTimeDao;

        public UpDateDateTimeTask(DateTimeDao dateTimeDao) {
            this.dateTimeDao = dateTimeDao;
        }

        @Override
        protected Void doInBackground(DateTime... dateTimes) {
            dateTimeDao.upDatetDateTime(dateTimes[0]);
            return null;
        }
    }

    public class DeleteDateTimeTask extends AsyncTask<DateTime, Void, Void> {
        private DateTimeDao dateTimeDao;

        public DeleteDateTimeTask(DateTimeDao dateTimeDao) {
            this.dateTimeDao = dateTimeDao;
        }

        @Override
        protected Void doInBackground(DateTime... dateTimes) {
            dateTimeDao.deleteDateTime(dateTimes[0]);
            return null;
        }
    }
}
