package com.khachsan.hotelmanament2.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.khachsan.hotelmanament2.model.ServiceUsing;
import com.khachsan.hotelmanament2.repository.ServiceUsingRepository;

import java.util.List;

public class ServiceUsingViewModel extends ViewModel {

    private ServiceUsingRepository repository;

    @ViewModelInject
    public ServiceUsingViewModel(ServiceUsingRepository repository) {
        this.repository = repository;
    }

    public void insertServiceUsing(ServiceUsing serviceUsing) {
        repository.insertServiceUsing(serviceUsing);
    }

    public void updateServiceUsing(ServiceUsing serviceUsing) {
        repository.updateServiceUsing(serviceUsing);
    }

    public void deleteServiceUsing(ServiceUsing serviceUsing) {
        repository.deleteServiceUsing(serviceUsing);
    }

    public LiveData<List<ServiceUsing>> getAllServiceUsingByHotelRoomNumber(int hotelRoomNumber) {
        return repository.getAllServiceUsingByDHotelRoomNumber(hotelRoomNumber);
    }
}
