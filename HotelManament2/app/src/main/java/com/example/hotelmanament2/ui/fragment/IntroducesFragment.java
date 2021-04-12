package com.example.hotelmanament2.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hotelmanament2.databinding.FragmentIntroduceBinding;

import dagger.hilt.android.AndroidEntryPoint;


public class IntroducesFragment extends Fragment {
    private FragmentIntroduceBinding binding;
    private static IntroducesFragment INSTANCES;

    public static IntroducesFragment getInstance() {
        if (INSTANCES == null) {
            INSTANCES = new IntroducesFragment();
        }
        return INSTANCES;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentIntroduceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
