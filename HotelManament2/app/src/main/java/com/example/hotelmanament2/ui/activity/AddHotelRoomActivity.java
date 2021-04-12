package com.example.hotelmanament2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.hotelmanament2.R;
import com.example.hotelmanament2.databinding.ActivityAddHotelRoomBinding;

import com.example.hotelmanament2.viewmodel.HotelRoomViewmodel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddHotelRoomActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityAddHotelRoomBinding binding;
    private HotelRoomViewmodel viewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddHotelRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        observeData();
    }

    private void observeData() {
        viewmodel.hotelRoomStatus.observe(this, hotelRoomStatus -> {
            switch (hotelRoomStatus) {
                case INSERT_HOTEL_ROOM_FAIL:
                    break;
                case INSERT_HOTEL_ROOM_SUCCESS:
                    Toast.makeText(this, "Thêm phòng thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    break;
            }
        });
    }

    private void initViews() {
        binding.tvSave.setOnClickListener(this);
        binding.tvCancel.setOnClickListener(this);
        binding.radioAirConditionRoom.setChecked(true);
        binding.radioEmptyRoom.setChecked(true);

        viewmodel = new ViewModelProvider(this).get(HotelRoomViewmodel.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSave:
                viewmodel.checkToSaveDataForHotelRoom(binding.tiedtRoomNnumber,
                        binding.tiedtNumberCustomerr,
                        binding.tiedtFristHourPrices,
                        binding.tiedtHourPrices,
                        binding.tiedtDayPrices,
                        binding.radioAirConditionRoom,
                        binding.radioEmptyRoom,
                        binding.radioStopRoom, binding.radioFanRoom);
                break;
            case R.id.tvCancel:
                finish();
                break;
        }
    }


}