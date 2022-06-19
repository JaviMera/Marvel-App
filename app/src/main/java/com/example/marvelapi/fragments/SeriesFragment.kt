package com.example.marvelapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.marvelapi.R
import com.example.marvelapi.adapters.series.SeriesAdapter
import com.example.marvelapi.databinding.FragmentSeriesBinding
import com.example.marvelapi.viewmodels.series.SeriesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeriesFragment : Fragment() {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!

    private val seriesViewModel: SeriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        val adapter = SeriesAdapter()
        binding.seriesList.adapter = adapter

        seriesViewModel.getSeries().observe(viewLifecycleOwner){
            lifecycleScope.launch{
                adapter.submitData(it)
            }
        }

        return binding.root
    }
}