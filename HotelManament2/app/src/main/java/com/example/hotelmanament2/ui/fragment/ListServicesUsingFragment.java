package com.example.hotelmanament2.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanament2.adapter.ServicesAdapter;
import com.example.hotelmanament2.databinding.FragmentListServicesUsingBinding;
import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.model.Service;
import com.example.hotelmanament2.ui.activity.AddServiceActivity;
import com.example.hotelmanament2.viewmodel.ServicesViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;


public class ListServicesUsingFragment extends Fragment {
    public static final int LIST_CUSTOMER_SERVICES = 123;
    private FragmentListServicesUsingBinding binding;

    private static ListServicesUsingFragment INSTANCES;
    private ServicesViewModel viewModel;
    private List<Service> services;

    public static ServicesAdapter adapter;

    public static ListServicesUsingFragment getInstance() {
        if (INSTANCES == null) {
            INSTANCES = new ListServicesUsingFragment();
        }
        return INSTANCES;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListServicesUsingBinding.inflate(inflater, container, false);

        services = new ArrayList<>();
        adapter = new ServicesAdapter(services);

        binding.rvServices.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.rvServices.setHasFixedSize(true);


        viewModel = new ViewModelProvider(requireActivity()).get(ServicesViewModel.class);
        viewModel.getAllServies().observe(getActivity(), services1 -> {
            adapter.submitListServices(services1);
        });
        binding.rvServices.setAdapter(adapter);
//        viewModel.createServices();
        setUpItemTouchHelper();

        binding.fabAddServices.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), AddServiceActivity.class);
            startActivity(intent);
        });


        return binding.getRoot();
    }


    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedHotelRoomPosition = viewHolder.getAdapterPosition();
                Service service = adapter.getServiceAt(swipedHotelRoomPosition);
                viewModel.deleteService(service);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Service is deleted", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rvServices);
    }


}
