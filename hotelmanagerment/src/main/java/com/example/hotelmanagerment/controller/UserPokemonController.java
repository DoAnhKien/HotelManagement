package com.example.hotelmanagerment.controller;


import com.example.hotelmanagerment.model.HotelRoom;
import com.example.hotelmanagerment.model.UserPokemon;
import com.example.hotelmanagerment.service.UserPokemonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-pokemon")
public class UserPokemonController {

    @Autowired
    private UserPokemonServices userPokemonServices;


    @PostMapping("/add")
    public UserPokemon insertOrSaveUserPokemon(@RequestBody UserPokemon userPokemon) {
        return userPokemonServices.insertOrUpDateHotelRoom(userPokemon);
    }

    @PostMapping("/delete/{id}")
    public void deleteUserPokemonById(@PathVariable int id) {
        userPokemonServices.deleteUserPokemonById(id);
    }

    @GetMapping("/all")
    public List<UserPokemon> getAllUserPokemon() {
        return userPokemonServices.getAllUserPokemons();
    }


    @PostMapping("/get/{id}")
    public UserPokemon getUserPokemonById(@PathVariable int id) {
        return userPokemonServices.getUserPokemonById(id);
    }

}
