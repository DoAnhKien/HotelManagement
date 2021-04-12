package com.example.hotelmanament2.viewmodel;

import android.text.TextUtils;
import android.widget.RadioButton;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.hotelmanament2.aenum.HotelRoomStatus;
import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.repository.HotelRoomRepository;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class HotelRoomViewmodel extends ViewModel {
    private HotelRoomRepository hotelRoomRopository;
    private LiveData<List<HotelRoom>> allHotelRoom;
    public MutableLiveData<HotelRoomStatus> hotelRoomStatus = new MutableLiveData<>();

    @ViewModelInject
    public HotelRoomViewmodel(HotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRopository = hotelRoomRepository;
        this.allHotelRoom = hotelRoomRopository.getAllHotelRoom();
    }


    public void createRoomHotel() {
        insertHotelRoom(new HotelRoom(101, "Dieu Hoa", "Con Trong", 2, 200, 120, 90));
        insertHotelRoom(new HotelRoom(101, "Dieu Hoa", "Con Trong", 2, 200, 120, 90));
        insertHotelRoom(new HotelRoom(101, "Dieu Hoa", "Con Trong", 2, 200, 120, 90));
    }

    public void checkToSaveDataForHotelRoom(TextInputLayout tiedtRoomNumber, TextInputLayout tiedtNumberCustomer, TextInputLayout tiedtFristHourPrices, TextInputLayout tiedtHourPrices, TextInputLayout tiedtDayPrices, RadioButton radioAirconditionRoom, RadioButton radioEmpty, RadioButton radioStop, RadioButton radioFanRoom) {
        if (!TextUtils.isEmpty(tiedtRoomNumber.getEditText().getText().toString()) &&
                !TextUtils.isEmpty(tiedtNumberCustomer.getEditText().getText().toString()) &&
                !TextUtils.isEmpty(tiedtFristHourPrices.getEditText().getText().toString()) &&
                !TextUtils.isEmpty(tiedtHourPrices.getEditText().getText().toString()) &&
                !TextUtils.isEmpty(tiedtDayPrices.getEditText().getText().toString())) {
            if (radioAirconditionRoom.isChecked()) {
                if (radioEmpty.isChecked()) {
                    int roomNumber = Integer.parseInt(tiedtRoomNumber.getEditText().getText().toString().trim());
                    int numberCustomer = Integer.parseInt(tiedtNumberCustomer.getEditText().getText().toString().trim());
                    int fristHourPrices = Integer.parseInt(tiedtFristHourPrices.getEditText().getText().toString().trim());
                    int hourPrices = Integer.parseInt(tiedtHourPrices.getEditText().getText().toString().trim());
                    int dayPrices = Integer.parseInt(tiedtDayPrices.getEditText().getText().toString().trim());
                    String roomStatus = radioEmpty.getText().toString();
                    String typeRoom = radioAirconditionRoom.getText().toString();
                    HotelRoom hotelRoom = new HotelRoom(roomNumber, typeRoom, roomStatus, numberCustomer, dayPrices, fristHourPrices, hourPrices);
                    insertHotelRoom(hotelRoom);
                    hotelRoomStatus.setValue(HotelRoomStatus.INSERT_HOTEL_ROOM_SUCCESS);
                } else if (radioStop.isChecked()) {
                    int roomNumber = Integer.parseInt(tiedtRoomNumber.getEditText().getText().toString().trim());
                    int numberCustomer = Integer.parseInt(tiedtNumberCustomer.getEditText().getText().toString().trim());
                    int fristHourPrices = Integer.parseInt(tiedtFristHourPrices.getEditText().getText().toString().trim());
                    int hourPrices = Integer.parseInt(tiedtHourPrices.getEditText().getText().toString().trim());
                    int dayPrices = Integer.parseInt(tiedtDayPrices.getEditText().getText().toString().trim());
                    String roomStatus = radioStop.getText().toString();
                    String typeRoom = radioAirconditionRoom.getText().toString();
                    HotelRoom hotelRoom = new HotelRoom(roomNumber, typeRoom, roomStatus, numberCustomer, dayPrices, fristHourPrices, hourPrices);
                    insertHotelRoom(hotelRoom);
                    hotelRoomStatus.setValue(HotelRoomStatus.INSERT_HOTEL_ROOM_SUCCESS);
                }
            } else if (radioFanRoom.isChecked()) {
                if (radioEmpty.isChecked()) {
                    int roomNumber = Integer.parseInt(tiedtRoomNumber.getEditText().getText().toString().trim());
                    int numberCustomer = Integer.parseInt(tiedtNumberCustomer.getEditText().getText().toString().trim());
                    int fristHourPrices = Integer.parseInt(tiedtFristHourPrices.getEditText().getText().toString().trim());
                    int hourPrices = Integer.parseInt(tiedtHourPrices.getEditText().getText().toString().trim());
                    int dayPrices = Integer.parseInt(tiedtDayPrices.getEditText().getText().toString().trim());
                    String roomStatus = radioEmpty.getText().toString();
                    String typeRoom = radioFanRoom.getText().toString();
                    HotelRoom hotelRoom = new HotelRoom(roomNumber, typeRoom, roomStatus, numberCustomer, dayPrices, fristHourPrices, hourPrices);
                    insertHotelRoom(hotelRoom);
                    hotelRoomStatus.setValue(HotelRoomStatus.INSERT_HOTEL_ROOM_SUCCESS);
                } else if (radioStop.isChecked()) {
                    int roomNumber = Integer.parseInt(tiedtRoomNumber.getEditText().getText().toString().trim());
                    int numberCustomer = Integer.parseInt(tiedtNumberCustomer.getEditText().getText().toString().trim());
                    int fristHourPrices = Integer.parseInt(tiedtFristHourPrices.getEditText().getText().toString().trim());
                    int hourPrices = Integer.parseInt(tiedtHourPrices.getEditText().getText().toString().trim());
                    int dayPrices = Integer.parseInt(tiedtDayPrices.getEditText().getText().toString().trim());
                    String roomStatus = radioStop.getText().toString();
                    String typeRoom = radioFanRoom.getText().toString();
                    HotelRoom hotelRoom = new HotelRoom(roomNumber, typeRoom, roomStatus, numberCustomer, dayPrices, fristHourPrices, hourPrices);
                    insertHotelRoom(hotelRoom);
                    hotelRoomStatus.setValue(HotelRoomStatus.INSERT_HOTEL_ROOM_SUCCESS);
                }
            }
        } else {
            tiedtRoomNumber.setError("Khong duoc de trong");
            tiedtNumberCustomer.setError("Khong duoc de trong");
            tiedtFristHourPrices.setError("Khong duoc de trong");
            tiedtHourPrices.setError("Khong duoc de trong");
            tiedtDayPrices.setError("Khong duoc de trong");
            hotelRoomStatus.setValue(HotelRoomStatus.INSERT_HOTEL_ROOM_FAIL);
        }
    }

    public void checkToUpdateHotelRoom(HotelRoom hotelRoom, String roomNumber1, String numberCustomer1, String fristHourPrices1, String hourPrices1, String dayPrices1,
                                       RadioButton radioAirConditionRoom, RadioButton radioFanRoom, RadioButton radioStopRoom, RadioButton radioEmptyRoom
    ) {
        if (!TextUtils.isEmpty(roomNumber1) && !TextUtils.isEmpty(numberCustomer1) && !TextUtils.isEmpty(fristHourPrices1) && !TextUtils.isEmpty(hourPrices1) && !TextUtils.isEmpty(dayPrices1)) {
            int roomNumber = Integer.parseInt(roomNumber1);
            int numberCustomer = Integer.parseInt(numberCustomer1);
            int fristHourPrices = Integer.parseInt(fristHourPrices1);
            int hourPrices = Integer.parseInt(hourPrices1);
            int dayPrices = Integer.parseInt(dayPrices1);

            hotelRoom.setRoomNumber(roomNumber);
            hotelRoom.setNumberCustomer(numberCustomer);
            hotelRoom.setDayPrice(dayPrices);
            hotelRoom.setFristHourPrice(fristHourPrices);
            hotelRoom.setHourPrice(hourPrices);

            if (radioAirConditionRoom.isChecked()) {
                hotelRoom.setTypeRoom(radioAirConditionRoom.getText().toString());
            } else if (radioFanRoom.isChecked()) {
                hotelRoom.setTypeRoom(radioFanRoom.getText().toString());
            }

            if (radioStopRoom.isChecked()) {
                hotelRoom.setStatus(radioStopRoom.getText().toString());
            } else if (radioEmptyRoom.isChecked()) {
                hotelRoom.setStatus(radioEmptyRoom.getText().toString());
            }
            updateHotelRoom(hotelRoom);
            hotelRoomStatus.setValue(HotelRoomStatus.UPDATE_HOTEL_ROOM_SUCCESS);
        } else {
            hotelRoomStatus.setValue(HotelRoomStatus.UPDATE_HOTEL_ROOM_FAIL);
        }
    }


    public void insertHotelRoom(HotelRoom hotelRoom) {
        hotelRoomRopository.insertHotelRoom(hotelRoom);
    }

    public void updateHotelRoom(HotelRoom hotelRoom) {
        hotelRoomRopository.updateHotelRoom(hotelRoom);
    }

    public void deleteHotelRoom(HotelRoom hotelRoom) {
        hotelRoomRopository.deleteHotelRoom(hotelRoom);
    }

    public LiveData<List<HotelRoom>> getAllHotelRoom() {
        return allHotelRoom;
    }
}
