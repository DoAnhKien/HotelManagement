package com.example.hotelmanagerment.repository;

import com.example.hotelmanagerment.model.User;
import com.example.hotelmanagerment.model.UserPokemon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserPokemonRepository extends CrudRepository<UserPokemon, Integer> {

}
