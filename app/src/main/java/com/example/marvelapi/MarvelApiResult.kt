package com.example.marvelapi

sealed class MarvelApiResult<out T : Any>{
    data class Success<out T: Any>(val data: T) : MarvelApiResult<T>()
    data class Error(val error: Throwable) : MarvelApiResult<Nothing>()
}