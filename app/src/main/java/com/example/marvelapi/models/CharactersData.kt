package com.example.marvelapi.models

import com.example.marvelapi.data.local.CharactersRepo

data class CharactersData(
    val results: List<Character>,
    val total: Int
)

data class CharactersRepoData(
    val results: List<CharactersRepo>,
    val total: Int
)