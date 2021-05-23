package com.example.hotelmanagerment.service;

import com.example.hotelmanagerment.model.HotelRoom;
import com.example.hotelmanagerment.model.UserPokemon;
import com.example.hotelmanagerment.repository.HotelRoomRepository;
import com.example.hotelmanagerment.repository.UserPokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserPokemonServices {

    @Autowired
    private UserPokemonRepository userPokemonRepository;


    public UserPokemon insertOrUpDateHotelRoom(UserPokemon userPokemon) {
        return userPokemonRepository.save(userPokemon);
    }

    public void deleteUserPokemonById(int id) {
        userPokemonRepository.deleteById(id);
    }

    public List<UserPokemon> getAllUserPokemons() {
        return (List<UserPokemon>) userPokemonRepository.findAll();
    }

    public UserPokemon getUserPokemonById(int id) {
        return userPokemonRepository.findById(id).get();
    }

    public long countHotelRoom() {
        return userPokemonRepository.count();
    }


}
