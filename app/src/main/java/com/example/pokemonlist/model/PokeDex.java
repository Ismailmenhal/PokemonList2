package com.example.pokemonlist.model;

import java.util.List;

public class PokeDex {
    private List<Pokemon> pokemon;

    public PokeDex() {
    }

    public PokeDex(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
