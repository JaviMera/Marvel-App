package com.example.marvelapi.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelapi.R
import com.example.marvelapi.models.characters.Character
import com.example.marvelapi.models.Thumbnail

val pattern = "\\(([^)]+)\\)".toRegex()

@BindingAdapter("showPoster")
fun bindCharacterThumbnail(imageView: ImageView, thumbnail: Thumbnail?){
    if(thumbnail?.path == null){
        imageView.setImageResource(R.drawable.character_image_error)
    }else{
        Glide.with(imageView.context)
            .load("${thumbnail.path.replace("http", "https")}/portrait_uncanny.${thumbnail.extension}")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_image)
                    .error(R.drawable.character_image_error))
            .into(imageView)
    }
}

@BindingAdapter("characterName")
fun bindCharacterName(textView: TextView, character: Character?){

    character?.let {
        pattern.find(it.name)?.groupValues?.get(1)?.let { characterName ->
            textView.text = "($characterName)"
        }
    }
}

@BindingAdapter("superheroName")
fun bindSuperheroName(textView: TextView, character: Character?){

    character?.let {
        textView.text =
            if(it.name.contains('(')){
                it.name.split('(')[0]
            }else{
                 it.name
            }
    }
}