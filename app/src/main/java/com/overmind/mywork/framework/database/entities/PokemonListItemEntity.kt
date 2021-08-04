package com.overmind.mywork.framework.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonListItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val url: String
)