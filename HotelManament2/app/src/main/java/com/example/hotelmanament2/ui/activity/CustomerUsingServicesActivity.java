package com.example.hotelmanament2.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hotelmanament2.R;
import com.example.hotelmanament2.adapter.ServicesAdapter;

import com.example.hotelmanament2.callback.MyServicesOnClicked;
import com.example.hotelmanament2.databinding.ActivityCustomerUsingServicesBinding;
import com.example.hotelmanament2.model.DateTime;
import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.model.Service;
import com.example.hotelmanament2.model.ServiceUsing;
import com.example.hotelmanament2.ui.fragment.ListServicesUsingFragment;
import com.example.hotelmanament2.viewmodel.DateTimeViewModel;
import com.example.hotelmanament2.viewmodel.ServiceUsingViewModel;
import com.example.hotelmanament2.viewmodel.ServicesViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CustomerUsingServicesActivity extends AppCompatActivity {

    private ActivityCustomerUsingServicesBinding binding;

    private ServicesViewModel viewModel;
    private ServiceUsingViewModel serviceUsingViewModel;
    private DateTimeViewModel dateTimeViewModel;

    private List<DateTime> dateTimes;

    private List<Service> services;

    private HotelRoom hotelRoom;

    private List<ServiceUsing> serviceUsings;

    public static ServicesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerUsingServicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        setItemTouchHelper();
    }

    private void initViews() {
        serviceUsings = new ArrayList<>();
        dateTimes = new ArrayList<>();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        hotelRoom = (HotelRoom) bundle.getSerializable("hotelroom");

        services = new ArrayList<>();
        adapter = new ServicesAdapter(services);

        binding.rvServices.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.rvServices.setHasFixedSize(true);

        dateTimeViewModel = new ViewModelProvider(this).get(DateTimeViewModel.class);
        dateTimeViewModel.getAllDateTimeByRoomNumber(hotelRoom.getRoomNumber()).observe(this, dateTimes1 -> {
            if (dateTimes1.size() == 0) {
                Toast.makeText(this, "Vui lòng lưu ngày sử dụng", Toast.LENGTH_SHORT).show();
            } else {
                dateTimes.addAll(dateTimes1);

            }
        });
        Log.d("kienda size date time", "" + GeneralInformationActivity.arrDateTimes.size());

        viewModel = new ViewModelProvider(this).get(ServicesViewModel.class);
        serviceUsingViewModel = new ViewModelProvider(this).get(ServiceUsingViewModel.class);

        viewModel.getAllServies().observe(this, services1 -> {
            adapter.submitListServices(services1);
        });

        adapter.setMyServicesOnClicked(new MyServicesOnClicked() {
            @Override
            public void handleMyServiceOnClicked(Service service, int position) {
                Toast.makeText(CustomerUsingServicesActivity.this, "123", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleMyServiceOnLongClicked(Service service, int position) {

            }
        });

        binding.rvServices.setAdapter(adapter);
    }

    private void setItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedServiceRoomPosition = viewHolder.getAdapterPosition();
                Service service = adapter.getServiceAt(swipedServiceRoomPosition);
                if (service.getStatusServices().contains("Ngừng kinh doanh")) {
                    Toast.makeText(CustomerUsingServicesActivity.this, "Dịch vụ này đã ngững kinh doanh", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                } else {
                    ServiceUsing serviceUsing = new ServiceUsing(service.getNameServices(), service.getTypeCount(), service.getPricesServices(), service.getStatusServices(), hotelRoom.getRoomNumber());
                    serviceUsingViewModel.insertServiceUsing(serviceUsing);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(CustomerUsingServicesActivity.this, "Sử dụng dịch vụ thành công", Toast.LENGTH_SHORT).show();
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rvServices);
    }
}