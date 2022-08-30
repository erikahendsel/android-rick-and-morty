package com.erikahendsel.rickandmorty

import com.erikahendsel.rickandmorty.network.ApiService

//Repository class is usually single source of truth (in short: ssot)
//if you are going to be depending on 2 data sources, for example, api and a local database then this
//class serves as mediator between the 2 sources ans knows where to get the data from so that the ViewModel
//is solely responsible for making the data available for the view to observe
class Repository(private val apiService: ApiService) {
    suspend fun getCharacters(page: String) = apiService.fetchCharacters(page)
}