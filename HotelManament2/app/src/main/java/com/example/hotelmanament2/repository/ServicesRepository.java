package com.example.hotelmanament2.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hotelmanament2.db.HotelRoomDao;
import com.example.hotelmanament2.db.ServicesDao;
import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.model.Service;

import java.util.List;

import javax.inject.Inject;

public class ServicesRepository {
    private ServicesDao servicesDao;
    private LiveData<List<Service>> allServices;

    @Inject
    public ServicesRepository(ServicesDao servicesDao) {
        this.servicesDao = servicesDao;
        this.allServices = servicesDao.getAllServices();
    }

    public void insertService(Service service) {
        new InsertServicesTask(servicesDao).execute(service);
    }

    public void updateService(Service service) {
        new UpdateServicesTask(servicesDao).execute(service);
    }

    public void deleteService(Service service) {
        new DeleteServicesTask(servicesDao).execute(service);
    }

    public LiveData<List<Service>> getAllServices() {
        return allServices;
    }

    private class InsertServicesTask extends AsyncTask<Service, Void, Void> {
        private ServicesDao servicesDao;

        public InsertServicesTask(ServicesDao servicesDao) {
            this.servicesDao = servicesDao;
        }

        @Override
        protected Void doInBackground(Service... services) {
            servicesDao.addServices(services[0]);
            return null;
        }
    }

    private class UpdateServicesTask extends AsyncTask<Service, Void, Void> {
        private ServicesDao servicesDao;

        public UpdateServicesTask(ServicesDao servicesDao) {
            this.servicesDao = servicesDao;
        }

        @Override
        protected Void doInBackground(Service... services) {
            servicesDao.updateServices(services[0]);
            return null;
        }
    }



    private class DeleteServicesTask extends AsyncTask<Service, Void, Void> {
        private ServicesDao servicesDao;

        public DeleteServicesTask(ServicesDao servicesDao) {
            this.servicesDao = servicesDao;
        }

        @Override
        protected Void doInBackground(Service... services) {
            servicesDao.deleteServices(services[0]);
            return null;
        }
    }
}
