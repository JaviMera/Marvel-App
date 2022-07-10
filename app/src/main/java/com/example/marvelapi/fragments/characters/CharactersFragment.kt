package com.example.marvelapi.fragments.characters

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.marvelapi.R
import com.example.marvelapi.adapters.characters.CharactersAdapter
import com.example.marvelapi.common.RecyclerAdapterBase
import com.example.marvelapi.viewmodels.characters.CharactersViewModel
import com.example.marvelapi.databinding.FragmentCharactersBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val charactersViewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.search -> {
                findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToSearchFragment("character"))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}