package com.khachsan.hotelmanament2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hotelmanament2.R;
import com.example.hotelmanament2.databinding.ActivityRegisterBinding;
import com.khachsan.hotelmanament2.model.User;
import com.khachsan.hotelmanament2.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding binding;
    private UserViewModel viewModel;
    private User currentUser;
    private List<User> arrUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        observeData();
    }

    private void initViews() {
        binding.btnSignUp.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        currentUser = new User();
        arrUsers = new ArrayList<>();
    }

    private void observeData() {
        viewModel.getCurrentUser().observe(this, user -> {
            Toast.makeText(this, "Đăng kí tải khoản thành công", Toast.LENGTH_SHORT).show();
        });

        viewModel.isRegisterFail.observe(this, error -> {
            switch (error) {
                case USER_EXSIST:
                    Toast.makeText(this, "Email này đã tồn tại", Toast.LENGTH_SHORT).show();
                    break;
                case USER_NULL:
                    Toast.makeText(this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                checkToLogin();
                break;
        }
    }

    private void checkToLogin() {
        viewModel.insertAUser(binding.edtEmail.getText().toString(),
                binding.edtUserName.getText().toString(),
                binding.edtPassword.getText().toString(),
                binding.edtRetypePassword.getText().toString());
    }


}