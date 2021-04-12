 package com.example.hotelmanament2.util;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BindingAdapter {

    @androidx.databinding.BindingAdapter("set_int_to_string")
    public static void bindRoomNumber(TextView tv, int roomNumber) {
        tv.setText(String.valueOf(roomNumber));
    }

    @androidx.databinding.BindingAdapter("set_image_view")
    public static void bindImageView(ImageView img, int link) {
        Glide.with(img).load(link).into(img);
    }

}
