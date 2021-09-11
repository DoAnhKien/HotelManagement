package com.khachsan.hotelmanament2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hotelmanament2.R;

import com.example.hotelmanament2.databinding.ActivityEditCustomerBinding;
import com.khachsan.hotelmanament2.model.Customer;
import com.khachsan.hotelmanament2.model.HotelRoom;
import com.khachsan.hotelmanament2.util.Const;
import com.khachsan.hotelmanament2.viewmodel.CustomerViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditCustomerActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityEditCustomerBinding binding;
    private CustomerViewModel viewModel;

    private Customer customer;
    private HotelRoom hotelRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDataFromListCustomerFragment();
        initViews();
        observeData();
    }

    private void observeData() {
        viewModel.customerStatus.observe(this, customerStatus -> {
            switch (customerStatus) {
                case UPDATE_CUSTOMER_FAIL:
                    Toast.makeText(this, "Vui lòng diền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    break;
                case UPDATE_CUSTOMER_SUCCESS:
                    finish();
                    Toast.makeText(this, "Cập nhật thông tin khách hàng thành công", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void getDataFromListCustomerFragment() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Const.KEY_BUNDLE_EDIT_CUSTOMER);
        customer = (Customer) bundle.getSerializable(Const.KEY_TO_EDIT_CUSTOMER);
        hotelRoom = (HotelRoom) bundle.getSerializable(Const.KEY_TO_EDIT_CUSTOMER_HOTEL);
        Toast.makeText(this, "kien: " + customer.getIdentityCard(), Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        viewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        binding.tvEditCustomerCancel.setOnClickListener(this);
        binding.tvEditCustomerSave.setOnClickListener(this);

        binding.tiedtCustomerName.setText(customer.getCustomerName());
        binding.tiedtCustomerDateOfBrth.setText(customer.getDateOfBirth());
        binding.tiedtCustomerIdentityCard.setText(customer.getIdentityCard());
//
        if (customer.getAvatarCustomer() == R.drawable.ic_female_user) {
            binding.radioCustomerGenderFemale.setChecked(true);
        }
        if (customer.getAvatarCustomer() == R.drawable.ic_male_user) {
            binding.radioCustomerGenderMale.setChecked(true);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvEditCustomerSave:
                viewModel.checkToUpdateCustomer(customer, binding.tiedtCustomerName,
                        binding.tiedtCustomerIdentityCard,
                        binding.tiedtCustomerDateOfBrth, binding.radioCustomerGenderMale, binding.radioCustomerGenderFemale);
                break;
            case R.id.tvEditCustomerCancel:
                finish();
                break;
        }
    }
}