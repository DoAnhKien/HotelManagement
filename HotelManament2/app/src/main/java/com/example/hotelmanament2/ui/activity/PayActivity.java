package com.example.hotelmanament2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hotelmanament2.adapter.ServiceUsingAdapter;
import com.example.hotelmanament2.databinding.ActivityPayBinding;
import com.example.hotelmanament2.model.DateTime;
import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.model.HotelRoomPay;
import com.example.hotelmanament2.model.Service;
import com.example.hotelmanament2.model.ServiceUsing;
import com.example.hotelmanament2.viewmodel.DateTimeViewModel;
import com.example.hotelmanament2.viewmodel.HotelRoomPayViewModel;
import com.example.hotelmanament2.viewmodel.ServiceUsingViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PayActivity extends AppCompatActivity {

    private DateTime dateTime;
    private ActivityPayBinding binding;

    private HotelRoomPayViewModel hotelRoomPayViewModel;
    private ServiceUsingViewModel serviceUsingViewModel;

    private List<ServiceUsing> serviceUsings;
    private ServiceUsingAdapter adapter;
    private HotelRoom hotelRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        getDataFromGeneralActivity();
        checkTotalMoneyForCheckOutHotelRoom();
        setItemTouchHelper();

    }

    private void setItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPosition = viewHolder.getAdapterPosition();
                ServiceUsing serviceUsing = adapter.getServiceUsingAt(swipedPosition);
                serviceUsingViewModel.deleteServiceUsing(serviceUsing);
                adapter.notifyDataSetChanged();
                Toast.makeText(PayActivity.this, "ServiceUsing is deleted", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rvServicesUsing);
    }

    private void initViews() {
        hotelRoomPayViewModel = new ViewModelProvider(this).get(HotelRoomPayViewModel.class);
        serviceUsingViewModel = new ViewModelProvider(this).get(ServiceUsingViewModel.class);
    }


    private void checkTotalMoneyForCheckOutHotelRoom() {
        serviceUsings = new ArrayList<>();
        adapter = new ServiceUsingAdapter(serviceUsings);
        binding.rvServicesUsing.setHasFixedSize(true);
        binding.rvServicesUsing.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.rvServicesUsing.setAdapter(adapter);

        serviceUsingViewModel.getAllServiceUsingByHotelRoomNumber(hotelRoom.getRoomNumber()).observe(this, serviceUsings1 -> {
            serviceUsings.clear();
            serviceUsings.addAll(serviceUsings1);
            adapter.notifyDataSetChanged();
            hotelRoomPayViewModel.checkTotalMoneyForCheckOutHotelRoom(hotelRoom, dateTime, serviceUsings);
        });

        hotelRoomPayViewModel.getHotelRoomPayByHotelRoomNumber(hotelRoom.getRoomNumber()).observe(this, hotelRoomPay -> {
            if (hotelRoomPay != null) {
                binding.tvTotalMoney.setText(String.valueOf(hotelRoomPay.getTotalMoney()));
                binding.tvDetailContent.setText(hotelRoomPay.getContent());
            } else {

            }
        });

    }

    private void getDataFromGeneralActivity() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        dateTime = (DateTime) bundle.getSerializable("datetime");
        hotelRoom = (HotelRoom) bundle.getSerializable("hotelroom1");
        binding.tvCustomerId.setText("ID của khách hàng " + dateTime.getId());
        binding.tvSetDatTime.setText("Ngày giờ vào là: " + dateTime.getDate() + " " + dateTime.getHour());
        binding.tvRoomRent.setText("Thuê phòng " + dateTime.getRoomNumber() + " vào " + dateTime.getHour() + " " + dateTime.getDate());
    }

}