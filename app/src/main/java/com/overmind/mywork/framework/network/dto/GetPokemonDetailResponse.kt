package com.overmind.mywork.framework.network.dto

data class GetPokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int
)