package com.khachsan.hotelmanament2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


import com.example.hotelmanament2.R;
import com.example.hotelmanament2.databinding.ActivityGeneralInformationBinding;
import com.khachsan.hotelmanament2.model.Customer;
import com.khachsan.hotelmanament2.model.DateTime;
import com.khachsan.hotelmanament2.model.HotelRoom;
import com.khachsan.hotelmanament2.ui.fragment.ListCustomerFragment;
import com.khachsan.hotelmanament2.util.Const;
import com.khachsan.hotelmanament2.viewmodel.DateTimeViewModel;


import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class GeneralInformationActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String INTENT_DATE_TIME = "datetime";
    public static final String INTENT_DATA = "data";
    private static final String INTENT_HOTEL_ROOM = "hotelroom1";
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ListCustomerFragment listCustomerFragment;
    public static HotelRoom hotelRoom;
    private ActivityGeneralInformationBinding binding;

    private List<Customer> customers;
    private int MAX_NUMBER_CUSTOMER;

    private DateTimeViewModel viewModel;
    public static List<DateTime> arrDateTimes;
    private DateTime dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGeneralInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDataFromMainActivity();
        initViews();
        observeData();

    }

    private void observeData() {
        viewModel.dateTimeStatus.observe(this, dateTimeStatus -> {
            switch (dateTimeStatus) {
                case INSERT_DATE_TIME_FAIL:
                    Toast.makeText(this, "Phòng này đã lưu ngày sử dụng", Toast.LENGTH_SHORT).show();
                    break;
                case INSERT_DATE_TIME_SUCCESS:
                    Toast.makeText(this, "Bắt đầu tính ngày sử dụng", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }


    private void getDataFromMainActivity() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Const.KEY_BUNDLE_TO_SEND_HOTEL_ROOM);
        hotelRoom = (HotelRoom) bundle.getSerializable(Const.KEY_TO_SEND_HOTEL_ROOM);
        MAX_NUMBER_CUSTOMER = hotelRoom.getNumberCustomer();
        binding.tvIntroduces.setText("Phiếu thuê phòng " + String.valueOf(hotelRoom.getRoomNumber()));
    }

    private void initViews() {
        customers = new ArrayList<>();
        customers = ListCustomerFragment.customers;

        arrDateTimes = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.linearLayout1, listCustomerFragment.getInstance());
        transaction.commit();

        binding.btnCancel1.setOnClickListener(this);
        binding.btnPay.setOnClickListener(this);
        binding.btnDateTimeSave.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(DateTimeViewModel.class);
        viewModel.getAllDateTimeByRoomNumber(hotelRoom.getRoomNumber()).observe(this, dateTimes -> {
            arrDateTimes.clear();
            arrDateTimes.addAll(dateTimes);
            if (arrDateTimes.size() != 0) {
                binding.tvDate.setText(arrDateTimes.get(0).getDate());
                binding.tvHour.setText(arrDateTimes.get(0).getHour());
            } else {
                dateTime = viewModel.getCurrentDateTime(hotelRoom.getRoomNumber());
                binding.tvHour.setText(dateTime.getDate());
                binding.tvDate.setText(dateTime.getHour());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPay:
                if (arrDateTimes.size() == 0) {
                    Toast.makeText(this, "Chưa lưu ngày", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent intent = new Intent(GeneralInformationActivity.this, PayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(INTENT_DATE_TIME, arrDateTimes.get(0));
                    bundle.putSerializable(INTENT_HOTEL_ROOM, hotelRoom);
                    intent.putExtra(INTENT_DATA, bundle);
                    startActivity(intent);
                }
                break;
            case R.id.btnCancel1:
                finish();
                break;
            case R.id.btnDateTimeSave:
                viewModel.saveDataForDateTime(arrDateTimes, dateTime);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        int sizePosition = ListCustomerFragment.getInstance().customers.size();
        Intent intent = new Intent();
        intent.putExtra("sizePosition", sizePosition);
        setResult(RESULT_OK, intent);
        finish();
    }
}