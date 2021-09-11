package com.khachsan.hotelmanament2.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.khachsan.hotelmanament2.db.ServiceUsingDao;
import com.khachsan.hotelmanament2.model.ServiceUsing;

import java.util.List;

import javax.inject.Inject;

public class ServiceUsingRepository {

    private ServiceUsingDao serviceUsingDao;

    @Inject
    public ServiceUsingRepository(ServiceUsingDao serviceUsingDao) {
        this.serviceUsingDao = serviceUsingDao;
    }

    public void insertServiceUsing(ServiceUsing serviceUsing) {
        new InsertServiceUsingTask(serviceUsingDao).execute(serviceUsing);
    }

    public void updateServiceUsing(ServiceUsing serviceUsing) {
        new UpdateServiceUsingTask(serviceUsingDao).execute(serviceUsing);
    }


    public void deleteServiceUsing(ServiceUsing serviceUsing) {
        new DeleteServiceUsingTask(serviceUsingDao).execute(serviceUsing);
    }

    public LiveData<List<ServiceUsing>> getAllServiceUsingByDHotelRoomNumber(int hotelRoomNumber) {
        return serviceUsingDao.getAllServiceUsingByHotelRoomNumber(hotelRoomNumber);
    }

    public class InsertServiceUsingTask extends AsyncTask<ServiceUsing, Void, Void> {
        private ServiceUsingDao serviceUsingDao;

        public InsertServiceUsingTask(ServiceUsingDao serviceUsingDao) {
            this.serviceUsingDao = serviceUsingDao;
        }

        @Override
        protected Void doInBackground(ServiceUsing... serviceUsings) {
            serviceUsingDao.insertServiceUsing(serviceUsings[0]);
            return null;
        }
    }

    public class UpdateServiceUsingTask extends AsyncTask<ServiceUsing, Void, Void> {
        private ServiceUsingDao serviceUsingDao;

        public UpdateServiceUsingTask(ServiceUsingDao serviceUsingDao) {
            this.serviceUsingDao = serviceUsingDao;
        }

        @Override
        protected Void doInBackground(ServiceUsing... serviceUsings) {
            serviceUsingDao.updateServiceUsing(serviceUsings[0]);
            return null;
        }
    }

    public class DeleteServiceUsingTask extends AsyncTask<ServiceUsing, Void, Void> {
        private ServiceUsingDao serviceUsingDao;

        public DeleteServiceUsingTask(ServiceUsingDao serviceUsingDao) {
            this.serviceUsingDao = serviceUsingDao;
        }

        @Override
        protected Void doInBackground(ServiceUsing... serviceUsings) {
            serviceUsingDao.deleteServiceUsing(serviceUsings[0]);
            return null;
        }
    }
}
