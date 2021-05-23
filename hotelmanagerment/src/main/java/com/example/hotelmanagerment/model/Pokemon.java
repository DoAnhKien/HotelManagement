package com.example.hotelmanagerment.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id()
    private int id;
    private String pokemonName;
    private String pokemonUrl;

    public Pokemon() {
    }

    public Pokemon(int id, String pokemonName, String pokemonUrl) {
        this.id = id;
        this.pokemonName = pokemonName;
        this.pokemonUrl = pokemonUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
