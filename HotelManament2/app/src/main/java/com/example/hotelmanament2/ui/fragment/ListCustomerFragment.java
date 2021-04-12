package com.example.hotelmanament2.ui.fragment;

import android.app.Dialog;
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


import com.example.hotelmanament2.adapter.CustomerAdapter;
import com.example.hotelmanament2.callback.MyCustomerOnClicked;
import com.example.hotelmanament2.databinding.DialogCustomItemHotelRoomBinding;
import com.example.hotelmanament2.databinding.DialogItemCustomerUsingServiceBinding;
import com.example.hotelmanament2.databinding.FragmentListCustomerBinding;
import com.example.hotelmanament2.model.Customer;
import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.ui.BottomSheetDialog;
import com.example.hotelmanament2.ui.activity.AddCustomerActivity;
import com.example.hotelmanament2.ui.activity.CustomerUsingServicesActivity;
import com.example.hotelmanament2.ui.activity.EditCustomerActivity;
import com.example.hotelmanament2.ui.activity.GeneralInformationActivity;
import com.example.hotelmanament2.util.Const;
import com.example.hotelmanament2.util.VerticalSpaceItemDecoration;
import com.example.hotelmanament2.viewmodel.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;


public class ListCustomerFragment extends Fragment {

    public static List<Customer> customers;
    private CustomerAdapter adapter;
    public static CustomerViewModel viewModel;
    private static ListCustomerFragment INSTANCES;
    private static HotelRoom hotelRoom;

    private FragmentListCustomerBinding binding;
    private DialogItemCustomerUsingServiceBinding dialogBinding;

    private int MAX_CUSTOMER_NUMER;

    public static ListCustomerFragment getInstance() {
        if (INSTANCES == null) {
            INSTANCES = new ListCustomerFragment();
        }
        return INSTANCES;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListCustomerBinding.inflate(inflater, container, false);


        hotelRoom = GeneralInformationActivity.hotelRoom;
        MAX_CUSTOMER_NUMER = hotelRoom.getNumberCustomer();

        customers = new ArrayList<>();

        adapter = new CustomerAdapter(customers);
        binding.rvCustomer.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.rvCustomer.addItemDecoration(new VerticalSpaceItemDecoration(Const.VERTICALSPACESHEIGHT));
        binding.rvCustomer.setAdapter(adapter);

        adapter.setMyCustomerOnClicked(new MyCustomerOnClicked() {
            @Override
            public void handleMyClicked(int posstion, Customer customer) {
                createDialog(hotelRoom, customer, posstion);
            }

            @Override
            public void handleMyCustomerOnLongClieck(int position, Customer customer) {
            }
        });

        viewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
        viewModel.getGetAllCustomer(hotelRoom.getRoomNumber()).observe(getActivity(), customers1 -> {
            customers.clear();
            customers.addAll(customers1);
            adapter.notifyDataSetChanged();
            adapter.submitListCustomer(customers1);
        });
//        viewModel.createCustomer();
        setItemTouchHelper();


        binding.fabAddCustomer.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), AddCustomerActivity.class);
            boolean checkToAddCustomer = viewModel.checkToAddCustomer(MAX_CUSTOMER_NUMER, customers.size());
            if (checkToAddCustomer) {
                startActivity(intent);
            } else if (!checkToAddCustomer) {
                Toast.makeText(getActivity(), "Vượt quá số lượng", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }


    private void createDialog(HotelRoom hotelRoom, Customer customer, int position) {
        Dialog dialog = new Dialog(getActivity());
        dialogBinding = DialogItemCustomerUsingServiceBinding.inflate(getLayoutInflater());
        dialogBinding.tvUserUseService.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CustomerUsingServicesActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Const.KEY_TO_CUSTOMER_USE_SERVICE, hotelRoom);
            intent.putExtra(Const.KEY_BUNDLE_CUSTOMER_USE_SERVICE, bundle);
            startActivity(intent);
        });
        dialogBinding.tvEditCustomer.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditCustomerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Const.KEY_TO_EDIT_CUSTOMER, customer);
            bundle.putSerializable(Const.KEY_TO_EDIT_CUSTOMER_HOTEL, hotelRoom);
            intent.putExtra(Const.KEY_BUNDLE_EDIT_CUSTOMER, bundle);
            startActivity(intent);
        });

        dialog.setContentView(dialogBinding.getRoot());
        dialog.show();
    }


    private void setItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Customer currentCustomer = adapter.getCustomerAt(viewHolder.getAdapterPosition());
                viewModel.deleteCustomer(currentCustomer);
                adapter.notifyDataSetChanged();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.rvCustomer);
    }

}
