package com.example.marvelapi.fragments.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.LENGTH_SHORT
import androidx.navigation.fragment.navArgs
import com.example.marvelapi.R
import com.example.marvelapi.databinding.FragmentCharacterBinding
import com.example.marvelapi.databinding.FragmentCharactersBinding
import com.example.marvelapi.models.characters.Character
import com.example.marvelapi.viewmodels.characters.CharactersViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val args: CharacterFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.character = args.character
        return binding.root
    }
}