package com.example.marvelapi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingSource
import com.example.marvelapi.MarvelApiResult
import com.example.marvelapi.adapters.comics.ComicsAdapter
import com.example.marvelapi.adapters.comics.ComicsPagerInterface
import com.example.marvelapi.adapters.comics.ComicsPagingSource
import com.example.marvelapi.viewmodels.comics.ComicsViewModel
import com.example.marvelapi.databinding.FragmentComicsBinding
import com.example.marvelapi.models.comics.ComicsResponse
import com.example.marvelapi.network.repositories.NetworkComicsInterface
import com.example.marvelapi.network.repositories.NetworkComicsRepository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
class ComicsFragment : Fragment() {

    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!

    private val comicsViweModel: ComicsViewModel by viewModel()
    private val comicsRepository: NetworkComicsInterface by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentComicsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        val adapter = ComicsAdapter()
        binding.comicsList.adapter = adapter

        comicsViweModel.getComics().observe(viewLifecycleOwner){
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        return binding.root
    }
}


