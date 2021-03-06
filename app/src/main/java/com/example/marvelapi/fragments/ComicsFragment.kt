package com.example.marvelapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.marvelapi.adapters.comics.ComicsAdapter
import com.example.marvelapi.common.RecyclerAdapterBase
import com.example.marvelapi.viewmodels.comics.ComicsViewModel
import com.example.marvelapi.databinding.FragmentComicsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicsFragment : Fragment() {

    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!

    private val comicsViewModel: ComicsViewModel by viewModel()

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

        val adapter = ComicsAdapter(RecyclerAdapterBase.OnItemClickListener {

        })
        binding.comicsList.adapter = adapter

        comicsViewModel.getComics().observe(viewLifecycleOwner){
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        return binding.root
    }
}


