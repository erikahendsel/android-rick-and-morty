package com.erikahendsel.rickandmorty

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erikahendsel.rickandmorty.network.ApiClient
import com.erikahendsel.rickandmorty.network.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository = Repository(ApiClient.apiService)) :
    ViewModel() {

        private var _charactersLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    //info: LiveData is not mutable
    val characterLiveData:LiveData<ScreenState<List<Character>?>>
        get() = _charactersLiveData

    init {
        fetchCharacter()
    }

    private fun fetchCharacter() {
        _charactersLiveData.postValue(ScreenState.Loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            //This log shows what Thread we are running on
            Log.d("MainViewModel", "${Thread.currentThread().name}")
            try {
                val client = repository.getCharacters("1")
                _charactersLiveData.postValue(ScreenState.Success(client.result))
            } catch(e: Exception) {
                _charactersLiveData.postValue(ScreenState.Error(e.message.toString(), null))
            }
        }
    }
}