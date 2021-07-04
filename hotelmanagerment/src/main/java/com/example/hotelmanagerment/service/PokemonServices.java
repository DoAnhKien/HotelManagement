package com.example.hotelmanagerment.service;

import com.example.hotelmanagerment.model.Pokemon;
import com.example.hotelmanagerment.model.UserPokemon;
import com.example.hotelmanagerment.repository.PokemonRepository;
import com.example.hotelmanagerment.repository.UserPokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServices {

    @Autowired
    private PokemonRepository repository;

    public List<Pokemon> getAllPokemons() {
        return (List<Pokemon>) repository.findAll();
    }

    public Pokemon findPokemonById(int id) {
        return repository.findPokemonById(id);
    }

}
