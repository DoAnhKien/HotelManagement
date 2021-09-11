package com.khachsan.hotelmanament2.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.example.hotelmanament2.R;
import com.example.hotelmanament2.databinding.ActivityAddCustomerBinding;
import com.khachsan.hotelmanament2.model.Customer;
import com.khachsan.hotelmanament2.model.HotelRoom;
import com.khachsan.hotelmanament2.ui.fragment.ListCustomerFragment;
import com.khachsan.hotelmanament2.viewmodel.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private static HotelRoom hotelRoom;
    private CustomerViewModel viewModel;
    private List<Customer> customers;
    private ActivityAddCustomerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        customers = new ArrayList<>();

        binding.radioCustomerGenderMale.setChecked(true);
        binding.tvCustomerSave.setOnClickListener(this);
        binding.tvCustomerCancel.setOnClickListener(this);
        hotelRoom = GeneralInformationActivity.hotelRoom;

        viewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        customers = ListCustomerFragment.customers;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCustomerSave:
                boolean saveDataCustomer = viewModel.savaDataCustomer(binding.tiedtCustomerName,
                        binding.tiedtCustomerDateOfBrth,
                        binding.tiedtCustomerIdentityCard,
                        binding.radioCustomerGenderFemale,
                        binding.radioCustomerGenderMale,
                        hotelRoom);
                if (saveDataCustomer) {
                    Toast.makeText(this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case R.id.tvCustomerCancel:
                finish();
                break;
        }
    }
}