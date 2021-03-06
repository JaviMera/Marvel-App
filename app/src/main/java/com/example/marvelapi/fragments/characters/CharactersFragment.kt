package com.example.marvelapi.fragments.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marvelapi.adapters.characters.CharactersAdapter
import com.example.marvelapi.common.RecyclerAdapterBase
import com.example.marvelapi.viewmodels.characters.CharactersViewModel
import com.example.marvelapi.databinding.FragmentCharactersBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val charactersViewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        val adapter = CharactersAdapter(RecyclerAdapterBase.OnItemClickListener {
            findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToCharacterFragment(it))
        })
        binding.charactersList.adapter = adapter

        charactersViewModel.getCharacters().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }

        return binding.root
    }
}