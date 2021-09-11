package com.khachsan.hotelmanament2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hotelmanament2.R;
import com.example.hotelmanament2.databinding.ActivityEditServicesBinding;
import com.khachsan.hotelmanament2.model.Service;
import com.khachsan.hotelmanament2.util.Const;
import com.khachsan.hotelmanament2.viewmodel.ServicesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditServicesActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityEditServicesBinding binding;
    private ServicesViewModel viewModel;

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditServicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDataFromShowListServiceActivity();
        initViews();
        observeData();
    }

    private void observeData() {
        viewModel.servicesStatus.observe(this, servicesStatus -> {
            switch (servicesStatus) {
                case UPDATE_SERVICE_FAIL:
                    Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    break;
                case UPDATE_SERVICES_SUCCESS:
                    Intent intent = new Intent(EditServicesActivity.this, ShowListServicesActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "Cập nhật thông tin dịch vụ thành công", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void initViews() {
        binding.btnEditCancelServices.setOnClickListener(this);
        binding.btnEditSaveServices.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(ServicesViewModel.class);
        binding.tiedServiceName.setText(service.getNameServices());
        binding.tiedServiceCount.setText(String.valueOf(service.getPricesServices()));
        binding.tiedTypeCount.setText(service.getTypeCount());

        if (service.getStatusServices().equals(binding.radioEmtyServices.getText())) {
            binding.radioEmtyServices.setChecked(true);
        } else {
            binding.radioStopServies.setChecked(true);
        }
    }

    private void getDataFromShowListServiceActivity() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Const.KEY_BUNDLE_TO_EDIT_SERVICE);
        service = (Service) bundle.getSerializable(Const.KEY_TO_EDIT_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditCancelServices:
                finish();
                break;
            case R.id.btnEditSaveServices:
                viewModel.updateServices(service, binding.tiedServiceName, binding.tiedTypeCount, binding.tiedServiceCount, binding.radioEmtyServices, binding.radioStopServies);
                break;
        }
    }
}