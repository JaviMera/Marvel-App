package com.example.marvelapi.comics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.marvelapi.databinding.FragmentComicsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
class ComicsFragment : Fragment() {

    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!

    private val comicsViweModel: ComicsViewModel by viewModel()
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


