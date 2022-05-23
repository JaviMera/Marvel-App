package com.example.marvelapi.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelapi.R
import com.example.marvelapi.models.CharacterThumbnail

@BindingAdapter("showPoster")
fun bindPictureOfDay(imageView: ImageView, thumbnail: CharacterThumbnail){

    Log.i("CharacterPicture", thumbnail.toString())
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