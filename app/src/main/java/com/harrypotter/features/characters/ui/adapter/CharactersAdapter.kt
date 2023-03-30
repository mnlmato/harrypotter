package com.harrypotter.features.characters.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.harrypotter.R
import com.harrypotter.coreui.imageloader.ImageLoader
import com.harrypotter.features.characters.vm.model.CharacterUI

class CharactersAdapter constructor(
    private val imageLoader: ImageLoader,
    private val listener: OnCharacterClickListener
) : RecyclerView.Adapter<CharactersViewHolder>() {

    private val charactersMutable = mutableListOf<CharacterUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            view = parent.inflate(R.layout.item_characters),
            listener = listener,
            imageLoader = imageLoader
        )
    }

    private fun ViewGroup.inflate(@LayoutRes viewId: Int) =
        LayoutInflater.from(context).inflate(viewId, this, false)

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.populate(charactersMutable[position])
    }

    override fun getItemCount(): Int = charactersMutable.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(characters: List<CharacterUI>) {
        charactersMutable.apply {
            clear()
            addAll(characters)
        }
        notifyDataSetChanged()
    }
}