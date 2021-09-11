package com.khachsan.hotelmanament2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotelmanament2.databinding.BottomSheetLayoutBinding;
import com.khachsan.hotelmanament2.ui.activity.CustomerUsingServicesActivity;
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
