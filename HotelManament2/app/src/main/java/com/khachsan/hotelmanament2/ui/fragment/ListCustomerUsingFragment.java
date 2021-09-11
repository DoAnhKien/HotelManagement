package com.khachsan.hotelmanament2.ui.fragment;

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


import com.khachsan.hotelmanament2.adapter.CustomerAdapter;
import com.khachsan.hotelmanament2.callback.MyCustomerOnClicked;
import com.example.hotelmanament2.databinding.FragmentListCustomerUsingBinding;
import com.khachsan.hotelmanament2.model.Customer;
import com.khachsan.hotelmanament2.viewmodel.CustomerViewModel;


import java.util.ArrayList;
import java.util.List;

public class ListCustomerUsingFragment extends Fragment {
    private FragmentListCustomerUsingBinding binding;

    private List<Customer> customers;
    public static CustomerAdapter adapter;
    private CustomerViewModel viewModel;

    private static ListCustomerUsingFragment INSTANCES;

    public static ListCustomerUsingFragment getInstance() {
        if (INSTANCES == null) {
            INSTANCES = new ListCustomerUsingFragment();
        }
        return INSTANCES;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListCustomerUsingBinding.inflate(inflater, container, false);

        customers = new ArrayList<>();
        adapter = new CustomerAdapter(customers);
        binding.rvCustomerUsing.setAdapter(adapter);
        binding.rvCustomerUsing.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        adapter.setMyCustomerOnClicked(new MyCustomerOnClicked() {
            @Override
            public void handleMyClicked(int posstion, Customer customer) {

            }

            @Override
            public void handleMyCustomerOnLongClieck(int position, Customer customer) {

            }
        });


        viewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
        viewModel.getAllCustomerUsing().observe(getActivity(), customers1 -> {
            adapter.submitListCustomer(customers1);
        });
        setUpItemTouchHelper();
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
                Customer customer = adapter.getCustomerAt(swipedHotelRoomPosition);
                viewModel.deleteCustomer(customer);
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Customer is deleted", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rvCustomerUsing);
    }
}
