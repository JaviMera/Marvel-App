package com.example.marvelapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val charactersViewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        charactersViewModel.getCharacters()

        charactersViewModel.characters.observe(this, Observer {
            try {
                Log.i("MainActivity", it.count().toString())
            }catch (exception: Exception){
                Log.i("MainActivity", exception.localizedMessage!!)
            }
        })
    }
}

