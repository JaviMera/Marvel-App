package com.example.marvelapi.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelapi.R
import com.example.marvelapi.models.Character
import com.example.marvelapi.models.CharacterThumbnail

val pattern = "\\(([^)]+)\\)".toRegex()

@BindingAdapter("showPoster")
fun bindPictureOfDay(imageView: ImageView, thumbnail: CharacterThumbnail){
    if(thumbnail.path == null){
        imageView.setImageResource(R.drawable.character_image_error)
    }else{
        Glide.with(imageView.context)
            .load("${thumbnail.path.replace("http", "https")}/standard_xlarge.${thumbnail.extension}")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_image)
                    .error(R.drawable.character_image_error))
            .into(imageView)
    }
}

@BindingAdapter("characterName")
fun bindCharacterName(textView: TextView, character: Character){
    textView.text = pattern.find(character.name)?.groupValues?.get(1) ?: ""
}

@BindingAdapter("superheroName")
fun bindSuperheroName(textView: TextView, character: Character){
    textView.text =
        if(character.name.contains('(')){
            character.name.split('(')[0]
        }else{
             character.name
        }
}