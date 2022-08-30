package com.erikahendsel.rickandmorty

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.erikahendsel.rickandmorty.databinding.ActivityMainBinding
import com.erikahendsel.rickandmorty.network.ApiClient
import com.erikahendsel.rickandmorty.network.Character
import com.erikahendsel.rickandmorty.network.CharacterResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Response

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.characterLiveData.observe(this) { state ->
            processCharactersResponse(state)
        }
    }

    private fun processCharactersResponse(state: ScreenState<List<Character>?>) {

        val pb = findViewById<ProgressBar>(R.id.progressBar)

        when (state) {
            is ScreenState.Loading -> {
                pb.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                pb.visibility = View.GONE
                if(state.data != null) {
                    val adapter = MainAdapter(state.data)
                    val recyclerView = findViewById<RecyclerView>(R.id.rvAllCharacters)
                    recyclerView?.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView?.adapter = adapter
                }

            }
            is ScreenState.Error -> {
                pb.visibility = View.GONE
                val view = pb.rootView
                Snackbar.make(view, state.message!!,Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
