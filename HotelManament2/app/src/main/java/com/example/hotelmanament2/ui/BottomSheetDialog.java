package com.example.hotelmanament2.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotelmanament2.databinding.BottomSheetLayoutBinding;
import com.example.hotelmanament2.databinding.DialogCustomItemHotelRoomBinding;
import com.example.hotelmanament2.databinding.DialogItemCustomerUsingServiceBinding;
import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.ui.activity.CustomerUsingServicesActivity;
import com.example.hotelmanament2.ui.activity.EditRoomActivity;
import com.example.hotelmanament2.ui.activity.GeneralInformationActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    private BottomSheetLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetLayoutBinding.inflate(inflater, container, false);

        binding.tvUserUseService.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CustomerUsingServicesActivity.class);
            startActivity(intent);
        });


        return binding.getRoot();
    }
}
