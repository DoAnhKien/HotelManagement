package com.example.hotelmanagerment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_pokemon")
public class UserPokemon {
    @Id()
    private int id;
    private String userEmail;
    private String pokemonName;
    private String pokemonUrl;

    public UserPokemon() {
    }

    public UserPokemon(int id, String userEmail, String pokemonName, String pokemonUrl) {
        this.id = id;
        this.userEmail = userEmail;
        this.pokemonName = pokemonName;
        this.pokemonUrl = pokemonUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonUrl() {
        return pokemonUrl;
    }

    public void setPokemonUrl(String pokemonUrl) {
        this.pokemonUrl = pokemonUrl;
    }
}
