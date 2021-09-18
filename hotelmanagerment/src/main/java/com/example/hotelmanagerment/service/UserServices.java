package com.example.hotelmanagerment.service;

import com.example.hotelmanagerment.model.User;
import com.example.hotelmanagerment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        User tempUser = userRepository.findUserByUserEmail(user.getUserEmail());
        if (tempUser != null) {
            return null;
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    public void deleteUerById(int id) {
        userRepository.deleteById(id);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByUserEmail(email);
    }

}
