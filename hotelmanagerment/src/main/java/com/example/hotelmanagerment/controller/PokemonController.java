package com.example.hotelmanagerment.controller;


import com.example.hotelmanagerment.model.Pokemon;
import com.example.hotelmanagerment.model.User;
import com.example.hotelmanagerment.service.PokemonServices;
import com.example.hotelmanagerment.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pokemon")
public class PokemonController {
    @Autowired
    private PokemonServices services;

    @GetMapping("/all")
    public List<Pokemon> getAllUser() {
        return services.getAllPokemons();
    }
}
