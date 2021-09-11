package com.khachsan.hotelmanament2.repository;

import com.khachsan.hotelmanament2.model.User;
import com.khachsan.hotelmanament2.network.UserServices;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

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
