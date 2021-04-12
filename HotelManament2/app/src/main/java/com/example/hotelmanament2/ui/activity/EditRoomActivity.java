package com.example.hotelmanament2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.hotelmanament2.R;
import com.example.hotelmanament2.databinding.ActivityEditRoomBinding;
import com.example.hotelmanament2.model.HotelRoom;

import com.example.hotelmanament2.viewmodel.HotelRoomViewmodel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditRoomActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityEditRoomBinding binding;
    private HotelRoom hotelRoom;
    private HotelRoomViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        observeData();
    }

    private void observeData() {
        viewmodel.hotelRoomStatus.observe(this, hotelRoomStatus -> {
            switch (hotelRoomStatus) {
                case UPDATE_HOTEL_ROOM_FAIL:
                    Toast.makeText(this, "Không được bỏ trống thông tin", Toast.LENGTH_SHORT).show();
                    break;
                case UPDATE_HOTEL_ROOM_SUCCESS:
                    Toast.makeText(this, "Cập nhật thông tin phòng thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditRoomActivity.this, MainActivity.class));
                    break;
            }
        });
    }

    private void initViews() {
        getDataFromMainFragment();
        viewmodel = new ViewModelProvider(this).get(HotelRoomViewmodel.class);

        binding.tiedtRoomNnumber.setText(String.valueOf(hotelRoom.getRoomNumber()));
        binding.tiedtNumberCustomerr.setText(String.valueOf(hotelRoom.getNumberCustomer()));
        binding.tiedtDayPrices.setText(String.valueOf(hotelRoom.getDayPrice()));
        binding.tiedtFristHourPrices.setText(String.valueOf(hotelRoom.getFristHourPrice()));
        binding.tiedtHourPrices.setText(String.valueOf(hotelRoom.getHourPrice()));

        checkStatusRoom();
        checkTypeRoom();

        binding.tvEditCancel.setOnClickListener(this);
        binding.tvEditSave.setOnClickListener(this);
    }

    private void checkStatusRoom() {
        if (hotelRoom.getStatus().equals(binding.radioEmptyRoom.getText())) {
            binding.radioEmptyRoom.setChecked(true);
        } else {
            binding.radioStopRoom.setChecked(true);
        }
    }

    private void checkTypeRoom() {
        if (hotelRoom.getTypeRoom().equals(binding.radioAirConditionRoom.getText())) {
            binding.radioAirConditionRoom.setChecked(true);
        } else if (hotelRoom.getTypeRoom().equals(binding.radioFanRoom.getText())) {
            binding.radioFanRoom.setChecked(true);
        }
    }

    private void getDataFromMainFragment() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        hotelRoom = (HotelRoom) bundle.getSerializable("hotelRoom");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvEditSave:
                checkForUpdateHotelRoom();
                break;
            case R.id.tvEditCancel:
                finish();
                break;
        }
    }

    private void checkForUpdateHotelRoom() {
        viewmodel.checkToUpdateHotelRoom(hotelRoom, binding.tiedtRoomNnumber.getText().toString().trim(),
                binding.tiedtNumberCustomerr.getText().toString().trim(), binding.tiedtFristHourPrices.getText().toString().trim(),
                binding.tiedtHourPrices.getText().toString().trim(), binding.tiedtDayPrices.getText().toString().trim(),
                binding.radioAirConditionRoom, binding.radioFanRoom, binding.radioStopRoom, binding.radioEmptyRoom
        );

    }

}