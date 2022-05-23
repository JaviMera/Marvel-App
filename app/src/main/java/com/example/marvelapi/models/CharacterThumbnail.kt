package com.example.marvelapi.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterThumbnail(
    val path: String?,
    val extension: String
) : Parcelable