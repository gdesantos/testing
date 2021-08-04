package com.overmind.mywork.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.overmind.mywork.databinding.ItemPokemonBinding
import com.overmind.core.domain.PokemonListItem
import com.squareup.picasso.Picasso

class PokemonListAdapter(
    private val items: List<PokemonListItem>,
    private val itemClicked: (PokemonListItem) -> Unit
) : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context))
        val holder = PokemonViewHolder(binding)
        holder.itemView.setOnClickListener {
            if(holder.adapterPosition != RecyclerView.NO_POSITION) {
                itemClicked.invoke(items[holder.adapterPosition])
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonListItem = items[position]
        holder.binding.name.text = pokemonListItem.name
        Picasso.get().load(pokemonListItem.buildImageUrl()).into(holder.binding.image)
    }

    override fun getItemCount() = items.count()

    class PokemonViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)
}