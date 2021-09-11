package com.khachsan.hotelmanament2.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.khachsan.hotelmanament2.aenum.DateTimeStatus;
import com.khachsan.hotelmanament2.model.DateTime;
import com.khachsan.hotelmanament2.repository.DateTimeRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DateTimeViewModel extends ViewModel {
    private DateTimeRepository repository;
    public MutableLiveData<DateTimeStatus> dateTimeStatus = new MutableLiveData<>();


    @ViewModelInject
    public DateTimeViewModel(DateTimeRepository repository) {
        this.repository = repository;
    }


    public DateTime getCurrentDateTime(int hotelRoomNumber) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String date = DateFormat.getDateInstance().format(calendar.getTime());
        String time = simpleDateFormat.format(calendar.getTime());
        long countTime = calendar.getTimeInMillis();
        DateTime dateTime = new DateTime(hotelRoomNumber, date, time, countTime);
        return dateTime;
    }

    public void saveDataForDateTime(List<DateTime> arrDateTimes, DateTime dateTime) {
        if (arrDateTimes.size() == 0) {
            insertDateTime(dateTime);
            dateTimeStatus.setValue(DateTimeStatus.INSERT_DATE_TIME_SUCCESS);
        } else {
            dateTimeStatus.setValue(DateTimeStatus.INSERT_DATE_TIME_FAIL);
        }
    }


    public void insertDateTime(DateTime dateTime) {
        repository.insertDateTime(dateTime);
    }

    public void upDateDateTime(DateTime dateTime) {
        repository.updateDateTime(dateTime);
    }

    public void deleteDateTime(DateTime dateTime) {
        repository.deleteDateTime(dateTime);
    }


    public LiveData<List<DateTime>> getAllDateTimeByRoomNumber(int roomNumber) {
        return repository.getAllDateTimeByRoomNumber(roomNumber);
    }
}
