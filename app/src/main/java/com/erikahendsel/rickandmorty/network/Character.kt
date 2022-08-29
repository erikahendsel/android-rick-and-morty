package com.erikahendsel.rickandmorty.network

import com.squareup.moshi.Json

data class Character(
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "species")
    val species: String,
    @Json(name = "gender")
    val gender: String
)

data class CharacterResponse(
    @Json(name = "results")
    val result: List<Character>
)