package com.khachsan.hotelmanament2.viewmodel;

import android.text.TextUtils;
import android.widget.RadioButton;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.khachsan.hotelmanament2.aenum.ServicesStatus;
import com.khachsan.hotelmanament2.model.Service;
import com.khachsan.hotelmanament2.repository.ServicesRepository;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ServicesViewModel extends ViewModel {
    private ServicesRepository servicesRepository;
    private LiveData<List<Service>> allServies;
    public MutableLiveData<ServicesStatus> servicesStatus = new MutableLiveData<>();

    @ViewModelInject
    public ServicesViewModel(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
        this.allServies = servicesRepository.getAllServices();
    }

    public void updateServices(Service service, TextInputEditText tiedtServiceName, TextInputEditText tiedTypeCount, TextInputEditText tiedtPricesServices, RadioButton radioEmptyButton, RadioButton radioStopButton) {
        if (!TextUtils.isEmpty(tiedtServiceName.getText().toString()) && !TextUtils.isEmpty(tiedtPricesServices.getText().toString()) && !TextUtils.isEmpty(tiedTypeCount.getText().toString())) {
            String serviceName = tiedtServiceName.getText().toString();
            String typeService = tiedTypeCount.getText().toString();
            Integer priceService = Integer.parseInt(tiedtPricesServices.getText().toString());
            String statusService = "";
            if (radioEmptyButton.isChecked()) {
                statusService = radioEmptyButton.getText().toString();
                service.setNameServices(serviceName);
                service.setTypeCount(typeService);
                service.setPricesServices(priceService);
                service.setStatusServices(statusService);
                updateService(service);
                servicesStatus.setValue(ServicesStatus.UPDATE_SERVICES_SUCCESS);
            } else if (radioStopButton.isChecked()) {
                statusService = radioStopButton.getText().toString();
                service.setNameServices(serviceName);
                service.setTypeCount(typeService);
                service.setPricesServices(priceService);
                service.setStatusServices(statusService);
                updateService(service);
                servicesStatus.setValue(ServicesStatus.UPDATE_SERVICES_SUCCESS);
            }

        } else {
            servicesStatus.setValue(ServicesStatus.UPDATE_SERVICE_FAIL);
        }


    }

    public void saveServices(String serviceName, String typeCount, String servicePrices1, RadioButton radioEmtyServices, RadioButton radioStopServies) {
        if (radioEmtyServices.isChecked()) {
            if (!TextUtils.isEmpty(serviceName) && !TextUtils.isEmpty(typeCount) && !TextUtils.isEmpty(servicePrices1)) {
                int servicePrices = Integer.parseInt(servicePrices1);
                Service service = new Service(serviceName, typeCount, servicePrices, radioEmtyServices.getText().toString());
                insertService(service);
                servicesStatus.setValue(ServicesStatus.INSERT_SERVICES_EMPTY_SUCCESS);
            } else {
                servicesStatus.setValue(ServicesStatus.INSERT_SERVICES_FAIL);
            }
        } else if (radioStopServies.isChecked()) {
            if (!TextUtils.isEmpty(serviceName) && !TextUtils.isEmpty(typeCount) && !TextUtils.isEmpty(servicePrices1)) {
                int servicePrices = Integer.parseInt(servicePrices1);
                Service service = new Service(serviceName, typeCount, servicePrices, radioStopServies.getText().toString());
                insertService(service);
                servicesStatus.setValue(ServicesStatus.INSERT_SERVICES_STOP_SUCCESS);
            } else {
                servicesStatus.setValue(ServicesStatus.INSERT_SERVICES_FAIL);
            }
        }
    }

    public void insertService(Service service) {
        servicesRepository.insertService(service);
    }

    public void updateService(Service service) {
        servicesRepository.updateService(service);
    }

    public void deleteService(Service service) {
        servicesRepository.deleteService(service);
    }

    public LiveData<List<Service>> getAllServies() {
        return allServies;
    }


}
