package com.example.hotelmanament2.network;

import com.example.hotelmanament2.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserServices {


    @GET("user/all")
    Observable<List<User>> getAllUser();

    @POST("user/add")
    Observable<User> insertAUser(@Body User user);

}
