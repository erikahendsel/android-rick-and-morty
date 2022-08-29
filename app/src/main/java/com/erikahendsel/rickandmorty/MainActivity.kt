package com.erikahendsel.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.erikahendsel.rickandmorty.databinding.ActivityMainBinding
import com.erikahendsel.rickandmorty.network.ApiClient
import com.erikahendsel.rickandmorty.network.CharacterResponse
import retrofit2.Response

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.apiService.fetchCharacters("1")

        client.enqueue(object : retrofit2.Callback<CharacterResponse> {

            override fun onResponse(
                call:retrofit2.Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ){
                if (response.isSuccessful) {
                    Log.d("characters", "${response.body()}")

                    val result = response.body()?.result
                    result?.let {
                        val adapter = MainAdapter(result)
                        val recyclerView = findViewById<RecyclerView>(R.id.rvAllCharacters)
                        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView?.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<CharacterResponse>, t: Throwable) {
                Log.d("failed", "${t.message}")
            }
        })
    }
}
