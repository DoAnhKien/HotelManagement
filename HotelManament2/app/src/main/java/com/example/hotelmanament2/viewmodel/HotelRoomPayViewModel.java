package com.example.hotelmanament2.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hotelmanament2.model.DateTime;
import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.model.HotelRoomPay;
import com.example.hotelmanament2.model.Service;
import com.example.hotelmanament2.model.ServiceUsing;
import com.example.hotelmanament2.repository.HotelRoomPayRepository;

import java.util.Calendar;
import java.util.List;

public class HotelRoomPayViewModel extends ViewModel {

    private HotelRoomPayRepository repository;
    private LiveData<List<HotelRoomPay>> hotelListLiveData;


    @ViewModelInject
    public HotelRoomPayViewModel(HotelRoomPayRepository repository) {
        this.repository = repository;
        hotelListLiveData = repository.getAllHotelRoomPay();
    }


    public void checkTotalMoneyForCheckOutHotelRoom(HotelRoom hotelRoom, DateTime dateTime, List<ServiceUsing> serviceUsings) {
        Calendar calendar = Calendar.getInstance();
        Long currentTime = calendar.getTimeInMillis();
        Log.d("kienda currentTime: ", String.valueOf(currentTime));
        Log.d("kienda dateTime: ", String.valueOf(dateTime.getCountTime()));

        long paymentForService = 0;

        for (ServiceUsing item : serviceUsings) {
            paymentForService = paymentForService + item.getPricesServices();
        }


        long result = (currentTime - dateTime.getCountTime());
        long newResult = result / (1000 * 60 * 60);
        long moneyTotal;

        if (newResult <= 1) {
            moneyTotal = hotelRoom.getFristHourPrice() + hotelRoom.getFristHourPrice() * newResult + paymentForService;
            String content = hotelRoom.getFristHourPrice() + " + " + hotelRoom.getFristHourPrice() + "*" + newResult + " + " + paymentForService + " (Tiền dịch vụ) " + " = " + moneyTotal;
            HotelRoomPay hotelRoomPay = new HotelRoomPay(hotelRoom.getRoomNumber(), content, moneyTotal);
            repository.insertHotelRoomPay(hotelRoomPay);
        } else if (newResult < 24 && newResult > 1) {
            moneyTotal = hotelRoom.getHourPrice() + hotelRoom.getHourPrice() * newResult + paymentForService;
            String content = hotelRoom.getHourPrice() + " + " + hotelRoom.getHourPrice() + "*" + newResult + " + " + paymentForService + " (Tiền dịch vụ) " + " = " + moneyTotal;
            HotelRoomPay hotelRoomPay = new HotelRoomPay(hotelRoom.getRoomNumber(), content, moneyTotal);
            repository.insertHotelRoomPay(hotelRoomPay);
        } else {
            newResult = newResult / 24;
            moneyTotal = hotelRoom.getDayPrice() + hotelRoom.getDayPrice() * newResult + paymentForService;
            String content = hotelRoom.getDayPrice() + " + " + hotelRoom.getDayPrice() + "*" + newResult + " + " + paymentForService + " (Tiền dịch vụ) " + " = " + moneyTotal;
            HotelRoomPay hotelRoomPay = new HotelRoomPay(hotelRoom.getRoomNumber(), content, moneyTotal);
            repository.insertHotelRoomPay(hotelRoomPay);
        }
    }

    public void insertHotelRoomPay(HotelRoomPay hotelRoomPay) {
        repository.insertHotelRoomPay(hotelRoomPay);
    }


    public void deleteHotelRoomPay(HotelRoomPay hotelRoomPay) {
        repository.deletehotelHotelRoomPay(hotelRoomPay);
    }


    public LiveData<HotelRoomPay> getHotelRoomPayByHotelRoomNumber(int hotelRoomNumber) {
        return repository.getHotelRoomPayByRoomNumber(hotelRoomNumber);
    }

    public LiveData<List<HotelRoomPay>> getAllHotelRoomPay() {
        return hotelListLiveData;
    }
}
