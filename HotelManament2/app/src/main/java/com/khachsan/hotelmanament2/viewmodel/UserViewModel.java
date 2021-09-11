package com.khachsan.hotelmanament2.viewmodel;

import android.text.TextUtils;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.khachsan.hotelmanament2.aenum.ErrorLogin;
import com.khachsan.hotelmanament2.aenum.ErrorRegister;
import com.khachsan.hotelmanament2.aenum.SuccessLogin;
import com.khachsan.hotelmanament2.model.User;
import com.khachsan.hotelmanament2.repository.UserRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;
    private MutableLiveData<List<User>> mListUser = new MutableLiveData<>();
    private MutableLiveData<User> currentUser = new MutableLiveData<>();
    public MutableLiveData<ErrorRegister> isRegisterFail = new MutableLiveData<>();

    public MutableLiveData<SuccessLogin> isLoginSuccess = new MutableLiveData<>();
    public MutableLiveData<ErrorLogin> isLoginFail = new MutableLiveData<>();


    @ViewModelInject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MutableLiveData<List<User>> getAllUser() {
        return mListUser;
    }

    public MutableLiveData<User> getCurrentUser() {
        return currentUser;
    }


    public void checkForLogin(List<User> users, String userName, String userPassword) {
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPassword)) {
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                if (user.getUserName().equals(userName) && user.getUserPassword().equals(userPassword) && user.getUserPermission().equals("worker")) {
                    isLoginSuccess.setValue(SuccessLogin.WORKER_ACCESS);
                    return;
                } else if (user.getUserName().equals(userName) && user.getUserPassword().equals(userPassword) && user.getUserPermission().equals("admin")) {
                    isLoginSuccess.setValue(SuccessLogin.ADMIN_ACCESS);
                    return;
                } else {

                }
            }
        } else if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPassword)) {
            isLoginFail.setValue(ErrorLogin.CAN_NOT_EMPTY);
        }
        isLoginFail.setValue(ErrorLogin.USER_NOT_EXSIST);
    }


    public void getAllUserOnServer() {
        userRepository.getAllUser().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(users -> {
                    mListUser.postValue(users);
                }, error -> {
                    error.printStackTrace();
                });
    }

    public void insertAUser(String userEmail, String userName, String userPassword, String userRetypePassword) {
        if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPassword) && !TextUtils.isEmpty(userRetypePassword)) {
            User user = new User(userEmail, userName, userPassword, "worker", userEmail);
            userRepository.insertAUser(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user1 -> {
                        currentUser.postValue(user1);
                    }, error -> {
                        error.printStackTrace();
                        isRegisterFail.setValue(ErrorRegister.USER_EXSIST);
                    });
        } else {
            isRegisterFail.setValue(ErrorRegister.USER_NULL);
        }
    }

}



