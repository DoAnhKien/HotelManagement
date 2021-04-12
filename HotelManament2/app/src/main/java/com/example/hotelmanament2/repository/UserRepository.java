package com.example.hotelmanament2.repository;

import android.os.AsyncTask;

import com.example.hotelmanament2.db.HotelRoomDao;
import com.example.hotelmanament2.db.ServicesDao;
import com.example.hotelmanament2.model.HotelRoom;
import com.example.hotelmanament2.model.Service;
import com.example.hotelmanament2.model.User;
import com.example.hotelmanament2.network.UserServices;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;

public class UserRepository {

    private UserServices services;

    @Inject
    public UserRepository(UserServices services) {
        this.services = services;
    }

    public Observable<List<User>> getAllUser() {
        return services.getAllUser();
    }

    public Observable<User> insertAUser(User user) {
        return services.insertAUser(user);
    }


}
