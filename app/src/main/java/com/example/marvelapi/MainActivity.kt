package com.example.marvelapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.marvelapi.adapters.CharactersAdapter
import com.example.marvelapi.databinding.ActivityMainBinding
import com.example.marvelapi.network.repositories.NetworkCharactersInterface
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigInteger
import java.security.MessageDigest

class MainActivity(
) : AppCompatActivity() {

    private val charactersViewModel: CharactersViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CharactersAdapter()
        binding.charactersList.adapter = adapter

        charactersViewModel.getCharacters()

        charactersViewModel.characters.observe(this, Observer {
            try {
                Log.i("MainActivity", it.count().toString())
                adapter.submitList(it)
            }catch (exception: Exception){
                Log.i("MainActivity", exception.localizedMessage!!)
            }
        })
    }
}

