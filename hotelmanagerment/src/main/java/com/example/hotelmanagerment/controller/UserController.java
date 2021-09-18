package com.example.hotelmanagerment.controller;

import com.example.hotelmanagerment.model.User;
import com.example.hotelmanagerment.model.UserPokemon;
import com.example.hotelmanagerment.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices services;

    @GetMapping("/all")
    public List<User> getAllUser() {
        return services.getAllUser();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return services.addUser(user);
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user) {
        return services.updateUser(user);
    }

    @PostMapping("/delete/{id}")
    public void deleteUserById(@PathVariable int id) {
        services.deleteUerById(id);
    }

    @PostMapping("/find/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return services.findUserByEmail(email);
    }

}
