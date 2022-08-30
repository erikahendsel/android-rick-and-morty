package com.erikahendsel.rickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erikahendsel.rickandmorty.network.Character
import coil.load
import coil.transform.CircleCropTransformation
import com.erikahendsel.rickandmorty.databinding.RvItemBinding

class MainAdapter(private val charactersList: List<Character>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.apply {
            val character = charactersList[position]
            tvName.text = character.name
            ivImage.load(character.image)
            tvGenderSpecies.text =  "${character.gender} ${character.species}"
        }
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }
}