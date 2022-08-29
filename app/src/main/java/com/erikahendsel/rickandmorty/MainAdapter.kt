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

class MainAdapter(val charactersList: List<Character>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(character: Character) {
            val name = itemView.findViewById<TextView>(R.id.tvName)
            val image = itemView.findViewById<ImageView>(R.id.ivImage)
            val genderSpecies = itemView.findViewById<TextView>(R.id.tvGenderSpecies)

            name.text = character.name
            image.load(character.image)
            genderSpecies.text = "${character.gender} ${character.species}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }
}