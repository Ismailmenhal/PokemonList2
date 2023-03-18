package com.example.pokemonlist.Retrofit;

import com.example.pokemonlist.model.PokeDex;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface interfacePokeman {
    @GET("pokedex.json")
    Observable<PokeDex> getPokemons();
}
