package com.example.hotelmanagerment.repository;

import com.example.hotelmanagerment.model.Pokemon;
import com.example.hotelmanagerment.model.UserPokemon;
import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    Pokemon findPokemonById(int id);

}
