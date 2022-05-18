package com.sun.listener.network

import com.skydoves.sandwich.ApiResponse
import com.sun.listener.model.PokemonInfo
import com.sun.listener.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {
    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): ApiResponse<PokemonInfo>
}
