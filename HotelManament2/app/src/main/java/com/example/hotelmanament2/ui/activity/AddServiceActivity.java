package com.example.hotelmanament2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.hotelmanament2.R;
import com.example.hotelmanament2.databinding.ActivityAddServiceBinding;
import com.example.hotelmanament2.model.Service;
import com.example.hotelmanament2.ui.fragment.ListServicesUsingFragment;
import com.example.hotelmanament2.viewmodel.ServicesViewModel;
import com.google.android.material.textfield.TextInputEditText;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAddServiceBinding binding;
    private ServicesViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        observeData();
    }

    private void observeData() {
        viewModel.servicesStatus.observe(this, servicesStatus -> {
            switch (servicesStatus) {
                case INSERT_SERVICES_FAIL:
                    Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    break;
                case INSERT_SERVICES_EMPTY_SUCCESS:
                    Intent emptyIntent = new Intent(this, ShowListServicesActivity.class);
                    Toast.makeText(this, "Thêm dịch vụ thành công", Toast.LENGTH_SHORT).show();
                    startActivity(emptyIntent);
                    break;
                case INSERT_SERVICES_STOP_SUCCESS:
                    Intent stopIntent = new Intent(this, ShowListServicesActivity.class);
                    Toast.makeText(this, " Thêm dịch vụ thành công", Toast.LENGTH_SHORT).show();
                    startActivity(stopIntent);
                    break;
            }
        });
    }

    private void initViews() {
        binding.radioEmtyServices.setChecked(true);
        viewModel = new ViewModelProvider(this).get(ServicesViewModel.class);
        binding.btnCancelServices.setOnClickListener(this);
        binding.btnSaveServices.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveServices:
                saveServices();
                break;
            case R.id.btnCancelServices:
                finish();
                break;
        }
    }

    private void saveServices() {
        viewModel.saveServices(binding.tiedServiceName.getText().toString(), binding.tiedTypeCount.getText().toString(),
                binding.tiedServiceCount.getText().toString(), binding.radioEmtyServices, binding.radioStopServies);
    }

}