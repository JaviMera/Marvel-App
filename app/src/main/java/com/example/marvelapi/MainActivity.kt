package com.example.marvelapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.marvelapi.adapters.CharactersAdapter
import com.example.marvelapi.databinding.ActivityMainBinding
import com.example.marvelapi.network.repositories.NetworkCharactersInterface
import kotlinx.coroutines.launch
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
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val adapter = CharactersAdapter()
        binding.charactersList.adapter = adapter

        charactersViewModel.getCharacters().observe(this, Observer {
            lifecycleScope.launch{
                adapter.submitData(it)
            }
        })
    }
}

