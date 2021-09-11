package com.khachsan.hotelmanament2.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hotelmanament2.R;
import com.example.hotelmanament2.databinding.ActivityLoginBinding;
import com.khachsan.hotelmanament2.model.User;
import com.khachsan.hotelmanament2.viewmodel.UserViewModel;


import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private List<User> users;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        observeData();
    }

    private void observeData() {
        viewModel.isLoginFail.observe(this, errorLogin -> {
            switch (errorLogin) {
                case CAN_NOT_EMPTY:
                    Toast.makeText(this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                    break;
                case USER_NOT_EXSIST:
                    Toast.makeText(this, "Tài khoản hoặc mật khẩu không tồn tại", Toast.LENGTH_SHORT).show();
                    break;
            }

        });
        viewModel.isLoginSuccess.observe(this, successLogin -> {
            switch (successLogin) {
                case ADMIN_ACCESS:
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    break;
                case WORKER_ACCESS:
                    startActivity(new Intent(LoginActivity.this, CustomerActivity.class));
                    break;
            }
        });
    }

    private void initViews() {
        users = new ArrayList<>();
        binding.btnLogin.setOnClickListener(this);
        binding.btnRegister.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.getAllUser().observe(this, users1 -> {
            users.clear();
            users.addAll(users1);
        });
        viewModel.getAllUserOnServer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                setUpForLogin();
                break;
            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void setUpForLogin() {
        viewModel.checkForLogin(users, binding.edtUserName.getText().toString(), binding.edtUserPassword.getText().toString());
    }

}