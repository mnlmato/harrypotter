package com.harrypotter.features.characters.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harrypotter.R
import com.harrypotter.coreui.imageloader.ImageLoader
import com.harrypotter.features.characters.vm.model.CharacterUI

typealias OnCharacterClickListener = (CharacterUI) -> Unit

class CharactersViewHolder(
    private val view: View,
    private val imageLoader: ImageLoader,
    listener: OnCharacterClickListener
) : RecyclerView.ViewHolder(view) {

    private lateinit var characterUI: CharacterUI

    private val pictureImageView = itemView.findViewById<ImageView>(R.id.pictureImageView)
    private val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
    private val houseTextView = itemView.findViewById<TextView>(R.id.houseTextView)

    init {
        view.setOnClickListener {
            if (absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                listener(characterUI)
            }
        }
    }

    fun populate(characterUI: CharacterUI) {
        this.characterUI = characterUI
        with(characterUI) {
            nameTextView.text = name
            houseTextView.text = house
            imageLoader.loadImage(pictureImageView, imageUrl)
        }
    }
}