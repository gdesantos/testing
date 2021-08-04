package com.overmind.core.domain

data class PokemonListItem(
    val name: String,
    val url: String
) {

    fun buildImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
    }
}