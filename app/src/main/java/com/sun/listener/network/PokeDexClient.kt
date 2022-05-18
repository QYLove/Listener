package com.sun.listener.network

import com.skydoves.sandwich.ApiResponse
import com.sun.listener.model.PokemonResponse
import javax.inject.Inject

class PokeDexClient @Inject constructor(
    private val pokeDexService:PokedexService
){

    suspend fun fetchPokemonList(
        page: Int
    ): ApiResponse<PokemonResponse> =
        pokeDexService.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )

    suspend fun fetchPokemonInfo(
        name: String
    ): ApiResponse<PokemonInfo> =
        pokeDexService.fetchPokemonInfo(
            name = name
        )

    companion object {
        private const val PAGING_SIZE = 20
    }

}