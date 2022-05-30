package com.example.marvelapi.exceptions

class MarvelException(message: String, val code: Int) : Exception(message)